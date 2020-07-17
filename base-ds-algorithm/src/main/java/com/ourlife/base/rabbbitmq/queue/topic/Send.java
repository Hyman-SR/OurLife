package com.ourlife.base.rabbbitmq.queue.topic;

import com.ourlife.base.rabbbitmq.ConnectionUtils;
import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 主题模式队列：路由模式是对路由键的完整匹配，主题模式是对路由键的模糊匹配
 * 符号“#”表示匹配一个或多个词，符号“*”表示匹配一个词。
 * confirm.tx 对应的队列不能收到 confirm.tx# 对应的exchange的消息
 *
 * @author zhangchao
 * @createdOn 2020/7/16
 */
public class Send {

    public static final Logger log = LoggerFactory.getLogger(Send.class);
    public static final String EXCHANGE_NAME = "test_exchange_topic";
    public static final String QUEUE_NAME = "test_queue_topic_A";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        //增删改查
        String msg = "增加商品";
        String routingKey = "goods.add";
        String msg1 = "删除商品";
        String routingKey1 = "goods.delete";
        String msg2 = "修改商品";
        String routingKey2 = "goods.update";
        String msg3 = "查询商品";
        String routingKey3 = "goods.query";
        channel.basicPublish(EXCHANGE_NAME, routingKey, null, msg.getBytes());
        channel.basicPublish(EXCHANGE_NAME, routingKey1, null, msg1.getBytes());
        channel.basicPublish(EXCHANGE_NAME, routingKey2, null, msg2.getBytes());
        channel.basicPublish(EXCHANGE_NAME, routingKey3, null, msg3.getBytes());
        log.debug("send msg ==> {}", msg);
        log.debug("send msg1 ==> {}", msg1);
        log.debug("send msg2 ==> {}", msg2);
        log.debug("send msg3 ==> {}", msg3);

        channel.close();
        connection.close();
    }
}
