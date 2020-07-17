package com.ourlife.base.rabbbitmq.product_confirm.confirm;

import com.ourlife.base.rabbbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author zhangchao
 * @createdOn 2020/7/17
 */
public class Send {

    public static final Logger log = LoggerFactory.getLogger(Send.class);
    public static final String EXCHANGE_NAME = "test_topic_confirm";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        String msg = "test confirm by confirm";
        String routingKey = "confirm.confirm";

        //开启确认机制保证可靠性，开启confirmSelect只是设置了服务器给回应，不进行waitForConfirms并不影响消息到达MQ实例
        channel.confirmSelect();

        //1.同步模式
//        channel.basicPublish(EXCHANGE_NAME, routingKey, null, msg.getBytes());
//        if (!channel.waitForConfirms()) {
//            log.debug("send msg failed");
//        } else {
//            log.debug("send ok");
//        }

        //2.异步模式，需要为每一个Channel维护一个unconfirm的消息序列集合，每发送一条消息，集合中加1；每回调一次handleAck方案，集合中删掉相应的记录(一条或多条)
        //这个unconfirm集合建议采用SortedSort存储结构。SDK中waitForConfirms()方法也是通过SortedSet维护消息序号的

        //未确认的消息标识
        final SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<>());
        //频道加一个监听
        channel.addConfirmListener(new ConfirmListener() {
            //标识消息发送成功
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                log.debug("handleAck ==> [deliveryTag : <{}>, multiple : <{}>]", deliveryTag, multiple);
                if (multiple) {
                    //清除小于 (deliveryTag + 1) 的所有元素
                    confirmSet.headSet(deliveryTag + 1).clear();
                } else {
                    confirmSet.remove(deliveryTag);
                }
            }

            //标识消息发送失败，可以设置1s后重试
            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                log.debug("handleAck ==> [deliveryTag : <{}>, multiple : <{}>]", deliveryTag, multiple);
                if (multiple) {
                    // TODO 对失败的消息拿出来重新投递
                    SortedSet<Long> unackSet = confirmSet.headSet(deliveryTag + 1);
                    //重新投递 xx
                    log.debug("消息已被重新投递 ==> {}", Arrays.toString(unackSet.toArray()));
                } else {
                    //重新投递
                    log.debug("消息已被重新投递 ==> {}", deliveryTag);
                }
            }
        });

        for (int i = 1; i <= 20; i++) {
            long seqNo = channel.getNextPublishSeqNo();
            String message = i + " -- " + msg;
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
            log.debug("投递消息 ==> seqNo = {}, message = {}", seqNo, message);
            confirmSet.add(seqNo);
            TimeUnit.MILLISECONDS.sleep(100);
        }


        channel.close();
        connection.close();
    }
}
