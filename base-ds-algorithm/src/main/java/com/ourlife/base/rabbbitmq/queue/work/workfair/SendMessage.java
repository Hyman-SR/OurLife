package com.ourlife.base.rabbbitmq.queue.work.workfair;

import com.ourlife.base.rabbbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.concurrent.TimeUnit;

/**
 * 公平的工作队列：多个消费者能者多劳，可用于负载均衡
 *
 * 公平分发：哪个消费者消费能力好，就给谁分发的快，需要设置channel.basicQos(prefetchCount=1);和手动ACK
 *
 * @author zhangchao
 * @createdOn 2020/7/13
 */
public class SendMessage {

    public static final String QUEUE_NAME = "test_work_fair_queue";

    public static void main(String[] args) throws Exception {
        Connection connection = null;
        Channel channel = null;
        try {
            connection = ConnectionUtils.getConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //每个消费者发送确认消息之前，消息队列不发送下一个消息到消费者，一次只处理一个消息，目的是限制发送给同一个消费者不得超过一条消息
            int prefetchCount = 1;
            channel.basicQos(prefetchCount);
            for (int i = 0; i < 50; i++) {
                String msg = "hello workUnfair - " + i;
                channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
                System.out.println("已发送消息 ==> " + msg);
                TimeUnit.MILLISECONDS.sleep(10);
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
