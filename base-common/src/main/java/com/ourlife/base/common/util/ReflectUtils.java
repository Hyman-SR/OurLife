package com.ourlife.base.common.util;

import java.lang.reflect.Method;

/**
 * 反射工具类
 *
 * @author zhangchao
 * @createdOn 2020/5/15
 */
public class ReflectUtils {

    public static Object invoke(Class clazz, String method, Object[] objects, Class<?>... parameterTypes) throws Exception {
        Class<?> aClass = Class.forName(clazz.getName());
        Method m = aClass.getMethod(method, parameterTypes);

        long start = System.currentTimeMillis();
        Object invoke = m.invoke(null, objects);
        System.out.println("该操作耗时：" + (System.currentTimeMillis() - start) + "ms");

        return invoke;
    }



}
