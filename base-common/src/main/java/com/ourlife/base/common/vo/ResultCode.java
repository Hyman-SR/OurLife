package com.ourlife.base.common.vo;

/**
 * @author zhangchao
 * @createdOn 2020/4/20
 */
public enum ResultCode implements IResultCode {
    /**
     * 请求成功
     */
    SUCCESS(200, "success"),

    /**
     * 请求失败
     */
    FAILED(500, "failed"),

    /**
     * 参数校验失败
     */
    VALIDATE_FAILED(404, "param is not valid"),

    /**
     * 该请求认证失败
     */
    UNAUTHORIZED(401, "this request is unauthorized"),

    /**
     * 该操作被禁止
     */
    FORBIDDEN(403, "this operation is forbidden");

    private long code;

    private String message;

    ResultCode(long code, String massage) {
        this.code = code;
        this.message = massage;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
