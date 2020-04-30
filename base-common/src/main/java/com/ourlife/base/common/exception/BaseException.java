package com.ourlife.base.common.exception;

import com.ourlife.base.common.vo.IResultCode;
import lombok.Data;

/**
 * 基础异常定义
 *
 * @author zhangchao
 * @createdOn 2020/4/30
 */
@Data
public class BaseException extends RuntimeException {

    private IResultCode resultCode;

    private String message;

    public BaseException() {

    }

    public BaseException(IResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
        this.message = resultCode.getMessage();
    }

    public BaseException(IResultCode resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
        this.message = message;
    }

}
