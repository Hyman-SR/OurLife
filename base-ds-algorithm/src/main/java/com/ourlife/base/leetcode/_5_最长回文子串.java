package com.ourlife.base.leetcode;

import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

/**
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 *
 * @author zhangchao
 * @createdOn 2020/7/22
 */
public class _5_最长回文子串 {

    public static void main(String[] args) throws Exception {
//        _5_最长回文子串 o = new _5_最长回文子串();
//        System.out.println(o.longestPalindrome("ab"));
    }

    /**
     * 动态规划：TODO 待研究：https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
     */
    private String longestPalindrome_动态规划(String s) {

        return null;
    }


    /**
     * 1.暴力解法
     */
    private String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        //遍历出所有的子串
        int maxLength = 1;
        int begin = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (j - i + 1 > maxLength && validPalindrome(chars, i, j)) {
                    maxLength = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLength);
    }

    private boolean validPalindrome(char[] chars, int start, int end) {
        while (start < end) {
            if (chars[start] != chars[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }


}
