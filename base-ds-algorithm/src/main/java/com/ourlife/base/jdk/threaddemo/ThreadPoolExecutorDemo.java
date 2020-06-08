package com.ourlife.base.jdk.threaddemo;

import java.util.concurrent.*;

/**
 * 线程池逻辑
 * 1.在创建了线程池后，等待提交过来的任务请求
 * 2.在调用execute()方法添加一个请求任务时，线程池会做如下判断：
 *  2.1 如果正在运行的线程数量小于corePoolSize，那么马上创建线程运行这个任务
 *  2.2 如果正在运行的线程数量大于或等于corePoolSize，那么将这个任务放入队列
 *  2.3 如果这个时候队列满了且正在运行的线程数量还小于maximumPoolSize，那么还是要创建非核心线程立刻运行这个任务
 *  2.4 如果队列满了且正在运行的线程数量大于或等于maximumPoolSize，那么线程池会启动饱和和拒绝策略来执行
 * 3.当一个线程完成任务时，它会从队列中取下一个任务来执行
 * 4.当一个线程无事可做超过一定时间keepAliveTime时，线程池会判断：如果当前运行的线程数大于corePoolSize，那么这个线程会被停掉
 *
 * @author zhangchao
 * @createdOn 2020/6/8
 */
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                5,
                100L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        try {
            for (int i = 0; i < 6; i++) {
                final int temp = i + 1;
                threadPoolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "号窗口，服务顾客" + temp);
                    try {
                        TimeUnit.SECONDS.sleep(4);
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
