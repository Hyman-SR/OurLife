package com.ourlife.base.api.mq.subscibe;

import com.ourlife.base.common.constants.MqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zhangchao
 * @createdOn 2020/4/30
 */
@Slf4j
@Component
public class DirectQueueSub {

    @RabbitListener(queues = MqConstants.DIRECT_QUEUE_A)
    public void process(Map map) {
        log.debug("DirectQueueSub 收到消息 ：{}", map);
    }
}
