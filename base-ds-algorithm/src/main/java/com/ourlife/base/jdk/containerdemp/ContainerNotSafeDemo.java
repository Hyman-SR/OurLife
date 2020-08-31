package com.ourlife.base.jdk.containerdemp;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * ArrayList
 * java.util.ConcurrentModificationException
 *
 * @author zhangchao
 * @createdOn 2020/6/1
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {
//        listNotSafe();
//        setNotSafe();
        mapNotSafe();
    }

    private static void mapNotSafe() {
//        Map<String, String> map = new HashMap<>();
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
    }
    }

    private static void setNotSafe() {
//        Set<String> set = new HashSet<>();
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    private static void listNotSafe() {
//        List<String> list = new ArrayList<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();

        /**
         *  ===========会出现 java.util.ConcurrentModificationException=======
         */
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

        /**
         * 1.故障现象 ： java.util.ConcurrentModificationException
         * 2.导致原因 ： 原生的ArrayList在遍历元素的时候，每一次都要去校验modCount是否和expectedModCount一致，当list被更改过，modCount就会变化，不一致就会提示以上异常
         * 3.解决方案 ：
         *  3.1 new Vector<>()
         *  3.2 Collections.synchronizedList(new ArrayList<>())
         *  3.3 new CopyOnWriteArrayList()：当进行写的时候，复制出一个副本进行操作，可以提高并发读。读写分离思想，读和写是不同的容器，当前容器会不编辑任何元素
         *
         */
    }
}
