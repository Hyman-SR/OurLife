package com.ourlife.base.leetcode.数组;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 *
 * @author zhangchao
 * @createdOn 2020/8/28
 */
public class _3_无重复字符的最长子串 {
    public static void main(String[] args) {
        _3_无重复字符的最长子串 o = new _3_无重复字符的最长子串();
        int maxLength = o.lengthOfLongestSubstring("abba");
        System.out.println(maxLength);
    }

    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0, start = 0, end;
        Map<Character, Integer> map = new HashMap<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            end = i;
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                start = Math.max(start, map.get(c) + 1);
            }
            maxLength = Math.max(maxLength, end - start + 1);
            map.put(c, i);
        }
        return maxLength;
    }
}


