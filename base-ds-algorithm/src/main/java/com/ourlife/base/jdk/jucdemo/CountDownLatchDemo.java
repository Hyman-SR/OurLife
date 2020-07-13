package com.ourlife.base.jdk.jucdemo;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhangchao
 * @createdOn 2020/6/3
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t task complete");
                countDownLatch.countDown();
            }, String.valueOf(i + 1)).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t all the task complete");
    }



}
