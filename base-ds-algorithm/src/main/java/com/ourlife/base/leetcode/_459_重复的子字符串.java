package com.ourlife.base.leetcode;

/**
 * https://leetcode-cn.com/problems/repeated-substring-pattern/
 * @author zhangchao
 * @createdOn 2020/8/24
 */
public class _459_重复的子字符串 {

    public static void main(String[] args) {

    }

    /**
     * s = 123123
     * s+s = 123123123123  从1开始找第一个s ,找到的就是   123 123123 123  ,即!= s.length
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        return (s + s).indexOf(s, 1) != s.length();
    }
}
