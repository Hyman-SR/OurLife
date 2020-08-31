package com.ourlife.base.rabbbitmq.product_confirm.confirm;

import com.ourlife.base.rabbbitmq.ConnectionUtils;
import com.ourlife.base.rabbbitmq.product_confirm.transaction.Send;
import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhangchao
 * @createdOn 2020/7/17
 */
public class Recv {
    public static final Logger log = LoggerFactory.getLogger(Send.class);
    public static final String EXCHANGE_NAME = "test_topic_confirm";
    public static final String QUEUE_NAME = "test.confirm.stack";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "confirm.confirm.#");

        channel.basicQos(1);

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    String msg = new String(body, "utf-8");
                    log.debug("resv msg ==> {}", msg);
                } finally {
                    channel.basicAck(envelope.getDeliveryTag(), false);
                    log.debug("done");
                }
            }
        };

        channel.basicConsume(QUEUE_NAME, false, consumer);
    }
}
