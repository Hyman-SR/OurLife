package com.ourlife.base.rabbbitmq.queue.topic;

import com.ourlife.base.rabbbitmq.ConnectionUtils;
import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author zhangchao
 * @createdOn 2020/7/16
 */
public class RecvA {

    public static final Logger log = LoggerFactory.getLogger(Send.class);
    public static final String EXCHANGE_NAME = "test_exchange_topic";
    public static final String QUEUE_NAME = "test_queue_topic_A";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //绑定队列
        String routingKey = "goods.#";
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, routingKey);

        //监听消息
        channel.basicQos(1);
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                log.debug("A recv msg ==> {}", msg);
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    log.debug("A done");
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };

        channel.basicConsume(QUEUE_NAME, false, consumer);
    }
}
