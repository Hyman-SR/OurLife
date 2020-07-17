package com.ourlife.base.rabbbitmq.queue.work.workUnfair;

import com.ourlife.base.rabbbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.concurrent.TimeUnit;

/**
 * 非公平的工作队列：多个消费者消费数量一致
 *
 * @author zhangchao
 * @createdOn 2020/7/13
 */
public class SendMessage {

    public static final String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws Exception {
        Connection connection = null;
        Channel channel = null;
        try {
            connection = ConnectionUtils.getConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            for (int i = 0; i < 50; i++) {
                String msg = "hello simple - " + i;
                channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
                System.out.println("已发送消息 ==> " + msg);
                TimeUnit.MILLISECONDS.sleep(100);
            }
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
