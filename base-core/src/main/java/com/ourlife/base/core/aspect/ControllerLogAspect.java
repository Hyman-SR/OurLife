package com.ourlife.base.core.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * web请求的日志切面
 *
 * @author zhangchao
 * @createdOn 2020/4/23
 */
@Aspect
@Component
@Order(2)
@Slf4j
public class ControllerLogAspect {

    @Pointcut("execution(public * com.ourlife.base.api.controller..*.*(..))")
    public void controllerLogAspect() {

    }

    @Around("controllerLogAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 获取当前请求
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        //记录请求内容
        log.info("request info ==> [ip : <{}>, http_method : <{}>, uri : <{}>, params : <{}>]",
                request.getRemoteAddr(),
                request.getMethod(),
                request.getRequestURI(),
                Arrays.toString(pjp.getArgs()));
        try {
            Object result = pjp.proceed();
            //打印返回的数据，产线不输出
            log.debug("response info ==> [http_method : <{}>, uri : <{}>, result : <{}>]",
                    request.getMethod(),
                    request.getRequestURI(),
                    result);
            return result;
        } catch (Throwable t) {
            log.error("[http_method : <{}>, uri : <{}>, error : <{}>]",
                    request.getMethod(),
                    request.getRequestURI(),
                    t);
            throw t;
        }
    }

}
