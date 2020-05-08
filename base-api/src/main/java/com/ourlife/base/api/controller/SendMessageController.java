package com.ourlife.base.api.controller;

import com.ourlife.base.api.model.form.mq.MessageCreateForm;
import com.ourlife.base.api.mq.publish.DirectQueuePub;
import com.ourlife.base.common.constants.ApiPathConstants;
import com.ourlife.base.common.util.ConditionsUtils;
import com.ourlife.base.common.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangchao
 * @createdOn 2020/4/29
 */
@RestController
@RequestMapping(value = ApiPathConstants.MESSAGES_API)
public class SendMessageController {

    @Autowired
    private DirectQueuePub directQueuePub;

    @PostMapping
    public BaseResult sendDirectMessage(@RequestBody MessageCreateForm form) {
        ConditionsUtils.checkArgument(null == form.getId() || form.getId() < 0);

        directQueuePub.send(form);
        return BaseResult.success();
    }
}
