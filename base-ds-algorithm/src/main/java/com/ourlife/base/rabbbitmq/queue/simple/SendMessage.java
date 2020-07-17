package com.ourlife.base.rabbbitmq.queue.simple;

import com.ourlife.base.rabbbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 简单队列：一个生产者对应一个消费者
 *
 * @author zhangchao
 * @createdOn 2020/7/13
 */
public class SendMessage {

    public static final String QUEUE_NAME = "test_simple_queue";

    public static void main(String[] args) throws Exception {
        Connection connection = null;
        Channel channel = null;
        try {
            connection = ConnectionUtils.getConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String msg = "hello simple";
            //开启确认机制
            channel.confirmSelect();
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        } finally {
            if (null != channel) {
                channel.close();
            }
            if (null != connection) {
                connection.close();
            }
        }
    }
}
