package com.ourlife.base.jdk.deadlockDemo;

/**
 * 死锁排查
 *
 * @author zhangchao
 * @createdOn 2020/6/1
 */
public class DeadLockDemo {

    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }

    private void deadLock() {
        new Thread(() -> {
            synchronized (A) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println("Thread 1");
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (B) {
                synchronized (A) {
                    System.out.println("Thread 2");
                }
            }
        }).start();
    }
}
