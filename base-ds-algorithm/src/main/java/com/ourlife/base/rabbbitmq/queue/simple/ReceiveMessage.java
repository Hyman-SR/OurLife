package com.ourlife.base.rabbbitmq.queue.simple;

import com.ourlife.base.rabbbitmq.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author zhangchao
 * @createdOn 2020/7/13
 */
public class ReceiveMessage {

    public static final String QUEUE_NAME = "test_simple_queue";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //定义消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            // TODO 基于spring的事件模型，如何理解？
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {

                String msg = new String(body, "utf-8");
                System.out.println("receive message ==> " + msg);
            }
        };

        //监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}

