package com.ourlife.base.common.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * 字符串全排列的工具类
 *
 * @author zhangchao
 * @createdOn 2020/5/15
 */
public class FullPermutationUtils {

    public static void main(String[] args) throws Exception {
        String str = "abc";
        ArrayList<String> list = (ArrayList) ReflectUtils.invoke(FullPermutationUtils.class, "fullPermutation", new Object[]{new String(str)}, new Class[]{String.class});
        System.out.println(Arrays.toString(list.toArray()));
    }

    /**
     * 全排列的入口
     *
     * @param str
     * @return
     */
    public static ArrayList<String> fullPermutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return res;
        }
        char[] chars = str.toCharArray();
        //默认使用字典顺序排序
        TreeSet<String> tempRes = new TreeSet<>();
        permutationCore(chars, tempRes, 0);
        res.addAll(tempRes);
        return res;
    }

    /**
     * 全排列的递归操作
     *
     * @param chars
     * @param tempRes
     * @param loc
     */
    private static void permutationCore(char[] chars, TreeSet<String> tempRes, int loc) {
        if (chars == null || chars.length == 0 || loc < 0 || loc > chars.length) {
            return;
        }
        if (loc == chars.length - 1) {
            //递归出口
            tempRes.add(String.valueOf(chars));
        } else {
            for (int i = loc; i < chars.length; i++) {
                swap(chars, i, loc);
                permutationCore(chars, tempRes, loc + 1);
                swap(chars, i, loc);
            }
        }
    }

    /**
     * 交换两个下标对应的值
     *
     * @param chars
     * @param a
     * @param b
     */
    private static void swap(char[] chars, int a, int b) {
        if (a == b) {
            return;
        }
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
    }
}
