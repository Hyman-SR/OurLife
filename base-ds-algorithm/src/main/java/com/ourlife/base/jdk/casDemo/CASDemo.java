package com.ourlife.base.jdk.casDemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangchao
 * @createdOn 2020/6/1
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5, 2020) + "改后的值" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 20200601) + "改后的值" + atomicInteger.get());
    }
}
