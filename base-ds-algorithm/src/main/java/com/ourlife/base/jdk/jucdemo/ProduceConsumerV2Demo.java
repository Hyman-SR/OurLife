package com.ourlife.base.jdk.jucdemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * (高并发环境)使用技术:volatile/cas/atomicInteger/BlockingQueue
 *
 * @author zhangchao
 * @createdOn 2020/6/3
 */
public class ProduceConsumerV2Demo {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource(new ArrayBlockingQueue<>(10));

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "生产者线程开始");
            try {
                shareResource.produce();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "生产者").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "消费者线程开始");
            try {
                shareResource.consume();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "消费者").start();

        //5s后停止 生产-消费动作
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            shareResource.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ShareResource {

    //默认开启，进行生产+消费
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue;

    public ShareResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void produce() throws Exception {
        String data;
        boolean returnValue;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            returnValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (returnValue) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "失败");
            }
            //休眠一秒
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("生产动作停止");
    }

    public void consume() throws Exception {
        String result;
        while (FLAG) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (null == result || "".equalsIgnoreCase(result)) {
                FLAG = false;
                System.out.println("队列已空，停止消费");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t 消费队列" + result + "成功");
        }
    }

    public void stop() throws Exception {
        this.FLAG = false;
    }
}