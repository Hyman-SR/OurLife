package com.ourlife.base.api.mq.publish;

import com.ourlife.base.common.constants.MqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author zhangchao
 * @createdOn 2020/4/30
 */
@Slf4j
@Component
public class DirectQueuePub implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public DirectQueuePub(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback(this);
    }

    public void send(Object object) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        log.debug("message publish ==> [ID : {}, body : {}]", correlationId, object.toString());
        rabbitTemplate.convertAndSend(MqConstants.DIRECT_EXCHANGE_A, MqConstants.DIRECT_TOUTING_KEY, object, correlationId);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.debug("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause);
        } else {
            log.debug("消息发送失败:correlationData({}),ack({}),cause({})", correlationData, ack, cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message);
    }
}
