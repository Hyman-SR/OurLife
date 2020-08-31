package com.ourlife.base.jdk.jucdemo.locksdemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：多线程之间按顺序调用，实现A->B->C三个线程启动，要求如下
 * AA打印5次，BB打印10次，CC打印15次
 * 紧接着，
 * AA打印5次，BB打印10次，CC打印15次
 * 。。。。
 * 重复10轮
 *
 * @author zhangchao
 * @createdOn 2020/6/3
 */
public class SyncAndReentrantLockDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            //重复10轮
            for (int i = 0; i < 10; i++) {
                shareData.printA();
            }
        }, "A").start();

        new Thread(() -> {
            //重复10轮
            for (int i = 0; i < 10; i++) {
                shareData.printB();
            }
        }, "B").start();

        new Thread(() -> {
            //重复10轮
            for (int i = 0; i < 10; i++) {
                shareData.printC();
                System.out.println("========第" + (i + 1) + "轮===========");
            }
        }, "C").start();
    }
}

/**
 * 共享资源
 */
class ShareData {

    // A=1,B=2,C=3
    private int number = 1;
    private Lock lock = new ReentrantLock();
    Condition cA = lock.newCondition();
    Condition cB = lock.newCondition();
    Condition cC = lock.newCondition();

    public void printA() {
        lock.lock();
        try {
            //当不由A执行时，就等待
            while (number != 1) {
                System.out.println("cA.await()");
                cA.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\tAA\t" + (i + 1));
            }
            //将数值指向B，准备唤醒B
            number = 2;
            cB.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            //当不由B执行时，就等待
            while (number != 2) {
                System.out.println("cB.await()");
                cB.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\tBB\t" + (i + 1));
            }
            //将数值指向B，准备唤醒C
            number = 3;
            cC.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            //当不由A执行时，就等待
            while (number != 3) {
                System.out.println("cC.await()");
                cC.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\tCC\t" + (i + 1));
            }
            //将数值指向B，准备唤醒B
            number = 1;
            cA.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
