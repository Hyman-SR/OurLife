package com.ourlife.base.jdk.jvmdemo.classloading;

/**
 * @author zhangchao
 * @createdOn 2020/7/20
 */
public class SubClass extends SuperClass {

    static {
        System.out.println("SubClass init");
    }


}
