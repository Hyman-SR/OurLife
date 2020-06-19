package com.ourlife.base.jdk.linuxcommanddemo;

import java.util.UUID;

/**
 * 一般服务问题的排查思路顺序依次为：整机 -> CPU -> 内存 -> 硬盘 -> 磁盘IO -> 网络IO
 *
 *
 * 常用linux指令
 * 1.top 查看整机情况，重点关注 %CPU和$MEM的情况，以及load average负载情况，其后的三个值分别为系统1min、5min、15min、的复杂情况https://www.cnblogs.com/kaituorensheng/p/3602805.html
 *  1.1 uptime，系统性能命令的精简版
 * 2.vmstat 查看CPU情况  vmstat -n 间隔时间(s) 次数
 *  2.1 mpstat -P ALL 间隔时间(s)  查看所有CPU核信息
 *  2.2 pidstat -u 间隔时间(s) 进程编号   查看每个进程使用CPU的用量分解信息
 * 3.free 查看内存情况  free -m、 free -g 显示的单位分别为MB、GB；
 *  3.1 pidstat -p 进程号 -r 间隔时间(s)  查看单个进程的
 * 4.df 查看硬盘情况   df -h
 * 5.iostat 查看磁盘IO情况
 *  5.1 pidstat -d 间隔时间(s) -p 进程号
 * 6.ifstat 查看网络IO情况
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
