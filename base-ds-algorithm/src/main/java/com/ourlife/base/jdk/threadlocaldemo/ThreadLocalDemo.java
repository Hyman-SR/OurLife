package com.ourlife.base.jdk.threadlocaldemo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangchao
 * @createdOn 2020/8/23
 */
public class ThreadLocalDemo {

    private static final int HASH_INCREMENT = 0x61c88647;

    public static void main(String[] args) {
        int length = 65536;
        Set<Integer> set = new HashSet<>(length);
        int hashCode = 0;
        for (int i = 0; i < length; i++) {
            hashCode = i * HASH_INCREMENT + HASH_INCREMENT;
            int bucket = hashCode & (length -1);
            set.add(bucket);
            System.out.println(i + "in bucket: " + bucket);
        }
        System.out.println(" ===   set.size:" + set.size());
    }

}
