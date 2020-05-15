package com.ourlife.base.api;

import com.ourlife.base.common.util.JVMUtils;

/**
 * @author zhangchao
 * @createdOn 2020/5/14
 */
public class JVMTest {

    public static void main(String[] args) {
        double totalMemory = JVMUtils.getJVMTotalMemory(JVMUtils.SizeUnit.GB);
        double maxMemory = JVMUtils.getJVMMaxMemory(JVMUtils.SizeUnit.GB);
        System.out.println("虚拟机内存总量(-Xms) = " + totalMemory );
        System.out.println("虚拟机最大内存总量(-Xmx) = " + maxMemory );
    }
}
