package com.ourlife.base.jdk.jvmdemo.classloading;

/**
 * @author zhangchao
 * @createdOn 2020/7/20
 */
public class SuperClass {

    static {
        System.out.println("SuperClass init");
    }

    public static int value = 123;
}
