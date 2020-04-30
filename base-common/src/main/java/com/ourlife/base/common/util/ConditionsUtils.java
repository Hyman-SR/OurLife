package com.ourlife.base.common.util;

import com.ourlife.base.common.exception.BaseException;
import com.ourlife.base.common.vo.IResultCode;
import com.ourlife.base.common.vo.ResultCode;

/**
 * 校验参数是否合法的工具类(采用正向逻辑)
 *
 * @author zhangchao
 * @createdOn 2020/4/30
 */
public final class ConditionsUtils {
    private ConditionsUtils() {

    }

    /**
     * 进行业务逻辑前的参数校验
     * 当expression为true时，则抛出异常
     *
     * @param expression
     */
    public static void checkArgument(boolean expression) {
        if (expression) {
            throw new BaseException(ResultCode.VALIDATE_FAILED);
        }
    }

    /**
     * 进行业务逻辑前的参数校验
     * 当expression为true时，则抛出异常
     *
     * @param expression
     * @param massage
     */
    public static void checkArgument(boolean expression, String massage) {
        if (expression) {
            throw new BaseException(ResultCode.VALIDATE_FAILED, massage);
        }
    }

    /**
     * 业务内的逻辑参数校验
     * 当expression为true时，则抛出异常
     *
     * @param expression
     * @param resultCode
     */
    public static void isTrue(boolean expression, IResultCode resultCode) {
        if (expression) {
            throw new BaseException(resultCode);
        }
    }

    /**
     * 业务内的逻辑参数校验
     * 当expression为true时，则抛出异常
     *
     * @param expression
     * @param resultCode
     * @param message
     */
    public static void isTrue(boolean expression, IResultCode resultCode, String message) {
        if (expression) {
            throw new BaseException(resultCode, message);
        }
    }

}
