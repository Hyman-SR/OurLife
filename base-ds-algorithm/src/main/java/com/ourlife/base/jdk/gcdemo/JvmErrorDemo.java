package com.ourlife.base.jdk.gcdemo;

import com.ourlife.base.common.util.JVMUtils;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangchao
 * @createdOn 2020/6/15
 */
public class JvmErrorDemo {

    private static int num;

    public static void main(String[] args) {
//        stackOverflowError();
//        oom_heap_space();
//        oom_gc_overhead_limit_exceeded();
//        oom_direct_buffer_memory();
//        oom_unable_create_new_native_thread();
        oom_metaspace();
    }

    /**
     * 会出现栈溢出：StackOverflowError
     */
    private static void stackOverflowError() {
        System.out.println("执行 StackOverflowError 方法" + (++num));
        while (true) {
            stackOverflowError();
        }
    }

    /**
     * 会出现内存溢出：OutOfMemoryError: c
     */
    private static void oom_heap_space() {
        System.out.println("执行 StackOverflowError 方法");
        byte[] bytes = new byte[1024 * 1024 * 10];
    }

    /**
     * 会出现内存溢出：OutOfMemoryError: GC overhead limit exceeded
     */
    private static void oom_gc_overhead_limit_exceeded() {
        List<Object> list = new ArrayList<>();
        int i = 0;
        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("==========" + i);
        }
    }

    /**
     * 会出现内存溢出：OutOfMemoryError: Direct buffer memory
     */
    private static void oom_direct_buffer_memory() {
        System.out.println("JVM能获取到的最大直接内存为：" + JVMUtils.getMaxDirectMemory(JVMUtils.SizeUnit.MB) + "MB");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ByteBuffer allocate = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }

    /**
     * 谨慎执行，多开几个进程，电脑就挂了
     * 会出现内存溢出：OutOfMemoryError: unable to create new native thread
     * 导致原因：
     *  应用创建了太多线程，一个应用进程创建多个线程，超过系统承载极限
     *  服务器不允许应用程序创建这么多线程，linux系统默认允许单个进程可以创建的线程数约为1024个
     * 解决办法：
     *  想办法降低应用程序创建线程数量，分析应用是否真的需要创建这么多线程，否则，将代码线程数降到最低
     *  对于有的应用，确实需要创建很多线程，远超过linux系统的默认1024的限制，可以通过修改linux服务器配置，扩大linux默认限制
     */
    private static void oom_unable_create_new_native_thread() {
        for (int i = 1; ; i++) {
            System.out.println("==============" + i);
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    /**
     * 会出现内存溢出：OutOfMemoryError: Metaspace
     * 元空间存放的信息：
     *  虚拟机加载的类信息
     *  常量池
     *  静态变量
     *  即时编译后的代码
     */
    private static void oom_metaspace() {
        int i = 1;
        try {
            i++;
            while (true) {
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(StaticClass.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, null);
                    }
                });
                enhancer.create();
            }
        } catch (Exception e) {
            System.out.println("创建了类的次数：" + i);
            e.printStackTrace();
        }
    }

    static class StaticClass {}
}
