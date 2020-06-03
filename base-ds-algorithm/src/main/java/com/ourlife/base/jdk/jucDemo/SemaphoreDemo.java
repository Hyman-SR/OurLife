package com.ourlife.base.jdk.jucDemo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangchao
 * @createdOn 2020/6/3
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        //模拟6个车位
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t 进入车位");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t 停车3秒，离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
