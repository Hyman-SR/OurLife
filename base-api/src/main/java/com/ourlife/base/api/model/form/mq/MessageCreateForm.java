package com.ourlife.base.api.model.form.mq;

import lombok.Data;

import java.io.Serializable;

/**
 * mq消息表单
 *
 * @author zhangchao
 * @createdOn 2020/4/29
 */
@Data
public class MessageCreateForm implements Serializable {
    private static final long serialVersionUID = -5245073657184395680L;

    private Integer Id;

    private Integer age;

    private String name;

    /**
     * 1：男；0：女
     */
    private Integer sex;
}
