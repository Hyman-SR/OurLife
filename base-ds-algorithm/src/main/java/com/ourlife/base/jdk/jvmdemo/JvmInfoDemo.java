package com.ourlife.base.jdk.jvmdemo;

import java.util.concurrent.TimeUnit;

/**
 * JVM配置-XX:+PrintFlagsInitial可打印出所有参数
 * 或者命令行执行 java -XX:+PrintFlagsInitial可打印所有参数
 *
 * java -XX:+PrintFlagsFinal -version 可查看所有参数和修改过的参数("="表示JVM默认的值，":="表示人为修改过的)
 * java -XX:+PrintFlagsFinal (设置参数，如：-XX:MetaspaceSize=256m; -Xss128k) T(运行的java类名),例：java -XX:+PrintFlagsFinal -XX:MetaspaceSize=256m 类名
 * java -XX:+PrintCommandLineFlags -version 查看JVM默认参数
 *
 * 查看正在运行的JVM的参数的方式
 * 1.jps获取jvm进程ID：jps -l
 * 2.jinfo 获取jvm某个参数信息：例：jinfo -flag PrintGCDetails 进程ID
 *
 * @author zhangchao
 * @createdOn 2020/6/10
 */
public class JvmInfoDemo {

    public static void main(String[] args) {
        byte[] bytes = new byte[1024 * 1024 * 1024];

        //开启一个jvm进程，用来做练习
        System.out.println("进程已开启。。");
        try {
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
