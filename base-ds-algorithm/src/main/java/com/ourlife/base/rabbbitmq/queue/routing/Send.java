package com.ourlife.base.rabbbitmq.queue.routing;

import com.ourlife.base.rabbbitmq.ConnectionUtils;
import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 路由模式队列：消息发送到交换器(direct)，根据路由键发送到相应路由键相同的队列
 *
 * @author zhangchao
 * @createdOn 2020/7/16
 */
public class Send {

    public static final Logger log = LoggerFactory.getLogger(Send.class);

    public static final String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        String msg = "hello direct";

        String routingKey = "A";
        channel.basicPublish(EXCHANGE_NAME, routingKey, true, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
        log.debug("send msg ==> {}", msg);
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText,
                                     String exchange, String routingKey,
                                     AMQP.BasicProperties basicProperties, byte[] body) throws IOException {
                String message = new String(body, "utf-8");
                System.out.println("Basic.Return 返回的结果是 ==> " + message);
                log.debug("return result ==> [replyCode : <{}>, replyText : <{}>, exchange : <{}>, " +
                                "routingKey : <{}>, message : <{}>]", replyCode, replyText, exchange,
                        routingKey, message);
            }
        });

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        channel.close();
        connection.close();
    }
}
