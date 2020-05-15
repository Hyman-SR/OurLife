package com.ourlife.base.common.util;


/**
 * 虚拟机相关的操作方法
 *
 * @author zhangchao
 * @createdOn 2020/5/14
 */
public class JVMUtils {

    public enum SizeUnit {
        GB((double) 1024 * 1024 * 1024),
        MB((double) 1024 * 1024),
        KB((double) 1024),
        BYTE((double) 1);
        private double value;

        public double getValue() {
            return value;
        }

        SizeUnit(double value) {
            this.value = value;
        }
    }

    /**
     * 获取虚拟机当前的内存总量
     *
     * @return 返回单位是MB
     */
    public static double getJVMTotalMemory(SizeUnit sizeUnit) {
        long totalMemory = Runtime.getRuntime().totalMemory();
        return (totalMemory / sizeUnit.getValue());
    }

    /**
     * 获取虚拟机可以使用的最大内存量
     *
     * @param sizeUnit
     * @return
     */
    public static double getJVMMaxMemory(SizeUnit sizeUnit) {
        long maxMemory = Runtime.getRuntime().maxMemory();
        return (maxMemory / sizeUnit.getValue());
    }
}
