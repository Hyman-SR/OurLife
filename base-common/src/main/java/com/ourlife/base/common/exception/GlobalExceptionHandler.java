package com.ourlife.base.common.exception;

import com.ourlife.base.common.vo.BaseResult;
import com.ourlife.base.common.vo.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

/**
 * 全局异常处理
 *
 * @author zhangchao
 * @createdOn 2020/4/20
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理已知的业务异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public BaseResult handleBaseException(BaseException ex) {
        return BaseResult.failed(ex.getResultCode(), ex.getMessage());
    }

    /**
     * 处理所有位置异常
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = {Exception.class, Throwable.class})
    @ResponseBody
    public BaseResult handleGenericException(Throwable ex, WebRequest request) {
        log.error("exception class ==> " + ex.getClass().getName());
        log.error("handling exception ==> " + request.getDescription(true));
        log.error("handle error msg:", ex);

        return BaseResult.failed(ResultCode.FAILED);
    }
}
