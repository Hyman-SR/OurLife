package com.ourlife.base.rabbbitmq.queue.routing;

import com.ourlife.base.rabbbitmq.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author zhangchao
 * @createdOn 2020/7/16
 */
public class RecvA {

    public static final String EXCHANGE_NAME = "test_exchange_direct";
    public static final String QUEUE_NAME = "test_queue_direct_A";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //每个消费者发送确认消息之前，消息队列不发送下一个消息到消费者，一次只处理一个消息，目的是限制发送给同一个消费者不得超过一条消息
        channel.basicQos(1);

        String routingKey = "A";
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, routingKey);

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {

                String msg = new String(body, "utf-8");
                System.out.println("A recv msg ==> " + msg);

                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("A done");
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };

        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }
}
