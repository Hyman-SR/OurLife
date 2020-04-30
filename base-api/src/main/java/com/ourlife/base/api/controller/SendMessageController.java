package com.ourlife.base.api.controller;

import com.google.common.base.Preconditions;
import com.ourlife.base.api.model.form.mq.MessageCreateForm;
import com.ourlife.base.common.constants.ApiPathConstants;
import com.ourlife.base.common.constants.MqConstants;
import com.ourlife.base.common.util.ConditionsUtils;
import com.ourlife.base.common.vo.BaseResult;
import com.ourlife.base.common.vo.ResultCode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangchao
 * @createdOn 2020/4/29
 */
@RestController
@RequestMapping(value = ApiPathConstants.MESSAGES_API)
public class SendMessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public BaseResult sendDirectMessage(@RequestBody MessageCreateForm form) {
        ConditionsUtils.checkArgument(null == form.getId() || form.getId() < 0);

        Map<String, Object> map = new HashMap<>(2);
        map.put("info", form);
        rabbitTemplate.convertAndSend(MqConstants.DIRECT_EXCHANGE_A, MqConstants.DIRECT_TOUTING_KEY, map);
        return BaseResult.success();
    }
}
