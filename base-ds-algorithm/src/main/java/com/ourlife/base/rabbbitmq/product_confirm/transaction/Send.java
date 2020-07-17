package com.ourlife.base.rabbbitmq.product_confirm.transaction;

import com.ourlife.base.rabbbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者发送消息到broke的可靠性保证：事务
 *
 * @author zhangchao
 * @createdOn 2020/7/17
 */
public class Send {
    public static final Logger log = LoggerFactory.getLogger(Send.class);
    public static final String EXCHANGE_NAME = "test_topic_confirm";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        String msg = "test confirm by tx";
        String routingKey = "confirm.tx.message";

        //开启事务机制来保证可靠性
        try {
            channel.txSelect();
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, msg.getBytes());
//            int i = 1 / 0;
            channel.txCommit();
            log.debug("txCommit...");
        } catch (Exception e) {
            channel.txRollback();
            log.debug("txRollback...");
        }

        channel.close();
        connection.close();
    }

}
