package com.ourlife.base.jdk.threaddemo;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 正式在项目中使用，避免直接使用JDK提供的以下四种线程池，因为默认参数会存在很多问题：https://www.cnblogs.com/gaopengpy/p/12148971.html
 * 1.newFixedThreadPool，内部的等待队列默认为Integer.MAX，可能会堆积非常多的请求，存在OOM的风险
 * 2.newSingleThreadExecutor，内部的等待队列默认为Integer.MAX，可能会堆积非常多的请求，存在OOM的风险
 * 3.newCachedThreadPool，内部的最大线程数是Integer.MAX，可能会创建非常多的线程，存在OOM的风险
 * 4.newScheduledThreadPool，内部的最大线程数是Integer.MAX，可能会创建非常多的线程，存在OOM的风险
 *
 * 线程池逻辑
 * 1.在创建了线程池后，等待提交过来的任务请求
 * 2.在调用execute()方法添加一个请求任务时，线程池会做如下判断：
 *  2.1 如果正在运行的线程数量小于corePoolSize，那么马上创建线程运行这个任务
 *  2.2 如果正在运行的线程数量大于或等于corePoolSize，那么将这个任务放入队列
 *  2.3 如果这个时候队列满了且正在运行的线程数量还小于maximumPoolSize，那么还是要创建非核心线程立刻运行这个任务
 *  2.4 如果队列满了且正在运行的线程数量大于或等于maximumPoolSize，那么线程池会启动饱和和拒绝策略来执行
 * 3.当一个线程完成任务时，它会从队列中取下一个任务来执行
 * 4.当一个线程无事可做超过一定时间keepAliveTime时，线程池会判断：
 *  如果当前运行的线程数大于corePoolSize，那么这个线程会被停掉
 *  所以线程池的所有任务完成后它最终会收缩到corePoolSize的大小
 *
 *  线程池参数如何合理设置？
 *  CPU密集型：即该任务需要大量的运损，没有阻塞(IO:磁盘IO，网络IO)，CPU一直全速运行，此类业务只有在真正的多核CPU上才可以通过多线程去做加速
 *      参考公式：CPU核数 + 1
 *  IO密集型：即该任务需要大量的IO，即大量的阻塞
 *      参考公式：CPU核数 / (1-阻塞系数)  ，阻塞系数在0.8 ~ 0.9之间
 *      如：8核CPU ： 8 / (1-0.9) = 80个线程数
 *
 *  JDK内置的拒绝策略(生产环境避免使用AbortPolicy，线程超卖后，让你怀疑人生，建议使用第二种：CallerRunsPolicy)：
 *  1.AbortPolicy(默认)：直接抛出RejectedExecutionException异常阻止系统正常运行
 *  2.CallerRunsPolicy："调度者运行"一种调节机制，该策略既不会抛弃任务，也不会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量
 *  3.DiscardOldestPolicy：抛弃队列中等待最久的任务，不抛出异常
 *  4.DiscardPolicy：直接丢弃任务，不予任何处理，也不抛出异常
 *
 * @author zhangchao
 * @createdOn 2020/6/8
 */
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {

//        // TODO 都包括哪些线程池类型？  https://www.cnblogs.com/gaopengpy/p/12148971.html
//        //内部的等待队列默认为Integer.MAX，可能会堆积非常多的请求，存在OOM的风险
//        Executors.newFixedThreadPool(10);
//        //内部的等待队列默认为Integer.MAX，可能会堆积非常多的请求，存在OOM的风险
//        Executors.newSingleThreadExecutor();
//        //内部的最大线程数是Integer.MAX，可能会创建非常多的线程，存在OOM的风险
//        Executors.newCachedThreadPool();
//        //内部的最大线程数是Integer.MAX，可能会创建非常多的线程，存在OOM的风险
//        Executors.newScheduledThreadPool(10);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                100L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                //1.AbortPolicy 超出线程池处理能力，直接抛异常RejectedExecutionException
//                new ThreadPoolExecutor.AbortPolicy());
                //2.CallerRunsPolicy 超出线程池处理能力，就将任务退还给任务调度线程，即main线程
//                new ThreadPoolExecutor.CallerRunsPolicy());
                //3.会丢弃阻塞队列中最老的任务
                new ThreadPoolExecutor.DiscardOldestPolicy());
                //4.会直接丢弃阻塞队列中的任务
//                new ThreadPoolExecutor.DiscardPolicy());
        try {
            for (int i = 0; i < 1000; i++) {
                final int temp = i + 1;
                threadPoolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "号窗口，服务顾客" + temp);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }

    }
}
