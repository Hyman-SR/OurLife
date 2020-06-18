package com.ourlife.base.jdk.linuxcommanddemo;

import java.util.UUID;

/**
 * 常用linux指令
 * 1.top 查看整机情况，重点关注 %CPU和$MEM的情况，以及load average负载情况，其后的三个值分别为系统1min、5min、15min、的复杂情况https://www.cnblogs.com/kaituorensheng/p/3602805.html
 *  1.1 uptime，系统性能命令的精简版
 *
 * @author zhangchao
 * @createdOn 2020/6/18
 */
public class LinuxCommandDemo {

    public static void main(String[] args) {
        while (true) {
            System.out.println(UUID.randomUUID().toString());
        }
    }
}
