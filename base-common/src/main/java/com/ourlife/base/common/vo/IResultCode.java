package com.ourlife.base.common.vo;

/**
 * @author zhangchao
 * @createdOn 2020/4/20
 */
public interface IResultCode {

    /**
     * 错误码
     *
     * @return
     */
    long getCode();

    /**
     * 错误信息提示
     *
     * @return
     */
    String getMessage();
}
