package com.ourlife.base.jdk.volatiledemo;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangchao
 * @createdOn 2020/5/28
 */
public class VolatileDemo {

    public static void main(String[] args) throws Exception {

        Data data = new Data();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in" );
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t update number value: " + data.number);
        }, "thread-1").start();


        while (data.number ==0) {

        }

        System.out.println(Thread.currentThread().getName() + "\t mission is over, and data.numer: " + data.number);
    }
}

class Data {
    //volatile 此处作用是内存可见性，保证每次获取都是从主内存中去获取最新值，而不是使用线程中的变量副本
    volatile int number = 0;

    public void addTo60() {
        this.number = 60;
    }
}
