package com.ourlife.base.jdk.threaddemo;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangchao
 * @createdOn 2020/6/5
 */
public class CallableDemo {

    public static void main(String[] args) {
        //执行callable的线程需要依赖FutureTask(同时支持Runnable和Callable)，获取这个线程的执行结果最好放到最后，负责会阻塞主线程
        //注：多个线程执行同一个futureTask，只会有一个线程执行成功，不会共同执行
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask, "A").start();

        //主线程继续执行其他不依赖的任务
        System.out.println("main thread do else task");

        //等待futureTask完成，再继续执行下一个依赖的任务
        while (!futureTask.isDone()) {

        }
        System.out.println("futureTask do complete, and do next task");
        System.out.println("all task done");

    }
}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("正在执行futureTask。。。。");
        //休眠5秒，标表示该线程执行任务的耗时
        TimeUnit.SECONDS.sleep(5);
        return 2002;
    }
}
