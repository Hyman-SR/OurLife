package com.ourlife.base.api.mq.subscibe;

import com.ourlife.base.common.constants.MqConstants;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zhangchao
 * @createdOn 2020/4/30
 */
@Slf4j
@Component
public class DirectQueueSub {

    @RabbitHandler
    @RabbitListener(queues = MqConstants.DIRECT_QUEUE_A)
    public void process(Message message, Channel channel) throws IOException {
        log.debug("DirectQueueSub 收到消息 ：{}, 线程名 : {}, 线程ID : {}", new String(message.getBody()), Thread.currentThread().getName(), Thread.currentThread().getId());
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }
}
