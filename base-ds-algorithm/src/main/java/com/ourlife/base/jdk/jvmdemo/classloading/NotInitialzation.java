package com.ourlife.base.jdk.jvmdemo.classloading;

/**
 * @author zhangchao
 * @createdOn 2020/7/20
 */
public class NotInitialzation {

    public static void main(String[] args) {
        System.out.println(SubClass.value);
    }
}
