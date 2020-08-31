package com.ourlife.base.jdk.jucdemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangchao
 * @createdOn 2020/6/3
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws Exception {
//       testArrayBlockingQueue();
        testSynchronousQueue();
    }

    public static void testSynchronousQueue() throws Exception {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t put 1");
                blockingQueue.put("1");

                System.out.println(Thread.currentThread().getName() + "\t put 2");
                blockingQueue.put("2");

                System.out.println(Thread.currentThread().getName() + "\t put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());

                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());

                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
    }

    private static void testArrayBlockingQueue() throws Exception {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(2);

        /*
        //往队列尾部增加元素，当超过限制会报错
        boolean addResult1 = blockingQueue.add("a1");
        boolean addResult2 = blockingQueue.add("a2");
        System.out.println("addResult : " + addResult1 + ", " + addResult2);
        //从队列头部删除元素，超过限制会报错
        Object removeResult1 = blockingQueue.remove();
        Object removeResult2 = blockingQueue.remove();
        System.out.println("removeResult : " + removeResult1 + "， " + removeResult2);
        //从队列头部获取元素，获取队列头部第一个元素，获取不到会报错
        Object elementResult1 = blockingQueue.element();
        Object elementResult2 = blockingQueue.element();
        System.out.println("elementResult : " + elementResult1 + ", " + elementResult2);
        */

        /*
        //往队列尾部增加元素，当超过限制会返回false
        boolean offerResult1 = blockingQueue.offer("b");
        boolean offerResult2 = blockingQueue.offer("b");
        boolean offerResult3 = blockingQueue.offer("b");
        System.out.println("offerResult : " + offerResult1 + ", "+ offerResult2 + ", " + offerResult3);

        //从队列头部删除元素，返回null
        Object pollResult1 = blockingQueue.poll();
        Object pollResult2 = blockingQueue.poll();
        Object pollResult3 = blockingQueue.poll();
        Object pollResult4 = blockingQueue.poll();
        System.out.println("pollResult : " + pollResult1 + ", "+ pollResult2 + ", " + pollResult3 + ", "+ pollResult4);

        //从队列头部获取元素，获取队列头部第一个元素，获取不到返回null
        Object peekResult1 = blockingQueue.peek();
        System.out.println("peekResult : " + peekResult1);
        */

        // 可设置时间，每隔timeout offer一个元素
        boolean offerResult1 = blockingQueue.offer("c", 2L, TimeUnit.SECONDS);
        boolean offerResult2 = blockingQueue.offer("c", 2L, TimeUnit.SECONDS);
        boolean offerResult3 = blockingQueue.offer("c", 2L, TimeUnit.SECONDS);
        System.out.println("offerResult : " + offerResult1 + ", "+ offerResult2 + ", " + offerResult3);

        // 可设置时间，每个timeout poll一个元素
        Object pollResult1 = blockingQueue.poll(2L, TimeUnit.SECONDS);
        Object pollResult2 = blockingQueue.poll(2L, TimeUnit.SECONDS);
        Object pollResult3 = blockingQueue.poll(2L, TimeUnit.SECONDS);
        System.out.println("pollResult : " + pollResult1 + ", "+ pollResult2 + ", " + pollResult3);
    }
}
