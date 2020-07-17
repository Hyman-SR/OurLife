package com.ourlife.base.rabbbitmq.queue.publish_subscribe;

import com.ourlife.base.rabbbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发布-订阅 队列：消息被发送到交换器，然后交换器会分发给所有绑定该交换器的队列，此处的交换器为fanout
 * 交换器类型：direct,fanout,topic,headers
 *
 * @author zhangchao
 * @createdOn 2020/7/15
 */
public class Send {

    public static final String EXCHANGE_NAME = "test_exchange_fanout";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String msg = "hello ps";

        //routingKey 为空标识fanout，会发送到exchange下所有的队列
        String routingKey = "";
        channel.basicPublish(EXCHANGE_NAME, routingKey, null, msg.getBytes());
        System.out.println("Send: " + msg);

        channel.close();
        connection.close();
    }
}
