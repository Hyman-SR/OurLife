package com.ourlife.base.common.vo;


import lombok.Data;

/**
 * 基础返回对象
 *
 * @author zhangchao
 * @createdOn 2020/4/20
 */
@Data
public class BaseResult<T> {

    private long code;

    private String message;

    private T data;

    protected BaseResult() {
    }

    protected BaseResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @return
     */
    public static <T> BaseResult<T> success() {
        return new BaseResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    /**
     * 成功返回结果
     *
     * @param data
     * @return
     */
    public static <T> BaseResult<T> success(T data) {
        return new BaseResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data
     * @param message
     * @return
     */
    public static <T> BaseResult<T> success(T data, String message) {
        return new BaseResult<>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     *
     * @param resultCode
     * @return
     */
    public static <T> BaseResult<T> failed(IResultCode resultCode) {
        return new BaseResult<>(resultCode.getCode(), resultCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     *
     * @param resultCode
     * @param massage
     * @return
     */
    public static <T> BaseResult<T> failed(IResultCode resultCode, String massage) {
        return new BaseResult<>(resultCode.getCode(), massage, null);
    }

}
