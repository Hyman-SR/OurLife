package com.ourlife.base.jdk.deadlockDemo;

/**
 * 死锁排查
 *
 * @author zhangchao
 * @createdOn 2020/6/1
 */
public class DeadLockDemo {

    private static String A = "a";
    private static String B = "b";

    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }

    private void deadLock() {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 请求获取锁A");
            synchronized (A) {
                System.out.println(Thread.currentThread().getName() + "\t 成功获取锁A");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t 请求获取锁B");
                synchronized (B) {
                    System.out.println(Thread.currentThread().getName() + "\t 成功获取锁B");
                }
            }
        }, "A").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 请求获取锁B");
            synchronized (B) {
                System.out.println(Thread.currentThread().getName() + "\t 成功获取锁B");
                System.out.println(Thread.currentThread().getName() + "\t 请求获取锁A");
                synchronized (A) {
                    System.out.println(Thread.currentThread().getName() + "\t 成功获取锁A");
                }
            }
        }, "B").start();
    }
}
