package com.ourlife.base.jdk.jucdemo.locksdemo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * synchronized、lock、都是可重入锁
 *
 * 共享资源的设计原则：
 *  读-读共存
 *  读-写不能共存
 *  写-写不能共存
 *
 *  写操作：原子+独占，整个过程必须是一个完整的统一体，中间不许被分割，被打断
 *
 * @author zhangchao
 * @createdOn 2020/6/2
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
//        MycacheNoLock mycacheNoLock = new MycacheNoLock();
//        MycacheNoLockWithLock mycacheNoLock = new MycacheNoLockWithLock();
        MycacheNoLockWithReentrantReadWriteLock mycacheNoLock = new MycacheNoLockWithReentrantReadWriteLock();

        int num = 10;
        for (int i = 1; i <= num; i++) {
            final int tempInt = i;
            new Thread(() -> {
                mycacheNoLock.put(tempInt + "", tempInt);
            }, String.valueOf(i)).start();
        }

        for (int i = num; i >= 1; i--) {
            final int tempInt = i;
            new Thread(() -> {
                mycacheNoLock.get(tempInt + "");
            }, String.valueOf(i)).start();
        }
    }
}

/**
 * 无锁实现，会存在并发问题
 */
class MycacheNoLock {

    private volatile Map<String, Object> map = new HashMap<>();

    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + "\t 正在写入:" + key);
        //暂停一会，模拟并发
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "\t 写入完成");
    }

    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + "\t 正在读取:" + key);
        //暂停一会，模拟并发
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Integer value = (Integer) map.get(key);
        System.out.println(Thread.currentThread().getName() + "\t 读取完成, value = " + value);
    }
}

/**
 * 加锁实现，同时会阻塞读，性能不足
 */
class MycacheNoLockWithLock {

    private volatile Map<String, Object> map = new HashMap<>();
    private Lock lock = new ReentrantLock();

    public void put(String key, Object value) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入:" + key);
            //暂停一会，模拟并发
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void get(String key) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取:" + key);
            //暂停一会，模拟并发
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Integer value = (Integer) map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成, value = " + value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 使用读写锁实现，提升并发读
 */
class MycacheNoLockWithReentrantReadWriteLock {

    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        reentrantReadWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入:" + key);
            //暂停一会，模拟并发
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        reentrantReadWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取:" + key);
            //暂停一会，模拟并发
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Integer value = (Integer) map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成, value = " + value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }
}
