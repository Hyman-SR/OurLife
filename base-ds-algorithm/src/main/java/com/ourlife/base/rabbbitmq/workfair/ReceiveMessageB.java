package com.ourlife.base.rabbbitmq.workfair;

import com.ourlife.base.rabbbitmq.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangchao
 * @createdOn 2020/7/13
 */
public class ReceiveMessageB {

    public static final String QUEUE_NAME = "test_work_fair_queue";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //每个消费者发送确认消息之前，消息队列不发送下一个消息到消费者，一次只处理一个消息，目的是限制发送给同一个消费者不得超过一条消息
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);
        //定义消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            // TODO 基于spring的事件模型，如何理解？
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {

                String msg = new String(body, "utf-8");
                System.out.println("B : receive message ==> " + msg);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("B : done");
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };

        //监听队列
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }
}
