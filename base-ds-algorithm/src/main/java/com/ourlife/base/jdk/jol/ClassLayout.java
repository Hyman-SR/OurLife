package com.ourlife.base.jdk.jol;

import org.openjdk.jol.vm.VM;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangchao
 * @createdOn 2020/8/22
 */
public class ClassLayout {

    public int a = 0;
    public long b = 0;
    public String c= "123";
    public Object d= null;
    public int e = 100;
    public static int f= 0;
    public static String g= "";
    public Object h= null;
    public boolean i;

    public static void main(String[] args) throws Exception {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.unlock();
        System.out.println(VM.current().details());
        System.out.println(org.openjdk.jol.info.ClassLayout.parseClass(ClassLayout.class).toPrintable());
        System.out.println("=========");
        Unsafe unsafe = getUnsafeInstance();
        ClassLayout classLayout = new ClassLayout();
        classLayout.a = 2;
        classLayout.b=3;
        classLayout.d = new HashMap<>();
        System.out.println(" a   ====================");
        long a_offset = unsafe.objectFieldOffset(ClassLayout.class.getDeclaredField("a"));
        System.out.println("a_offset = " + a_offset);
        int a_value = unsafe.getInt(classLayout, a_offset);
        System.out.println("a_value = " + a_value);
        System.out.println(" b   ====================");
        long b_offset = unsafe.objectFieldOffset(ClassLayout.class.getDeclaredField("b"));
        System.out.println("b_offset = " + b_offset);
        int b_value = unsafe.getInt(classLayout, b_offset);
        System.out.println("a_value = " + b_value);
    }

    public static Unsafe getUnsafeInstance() throws Exception {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        return (Unsafe) theUnsafe.get(Unsafe.class);
    }
}
