package com.ourlife.base.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 无重复字符的最长子串：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 *
 * @author zhangchao
 * @createdOn 2020/7/22
 */
public class _3_无重复字符的最长子串 {

    public static void main(String[] args) {
        _3_无重复字符的最长子串 o = new _3_无重复字符的最长子串();
//        System.out.println(o.lengthOfLongestSubstring("abcabcbb"));
//        System.out.println(o.lengthOfLongestSubstring("bbbbb"));
//        System.out.println(o.lengthOfLongestSubstring("pwwkew"));
//        System.out.println(o.lengthOfLongestSubstring(" "));

//        System.out.println(o.lengthOfLongestSubstring_精选("abcabcbb"));
//        System.out.println(o.lengthOfLongestSubstring_精选("bbbbb"));
//        System.out.println(o.lengthOfLongestSubstring_精选("pwwkew"));
        System.out.println(o.lengthOfLongestSubstring_精选("abba"));
    }

    /**
     * 滑动窗口
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring_精选(String s) {
        //记录最大不重复字符串长度
        int maxLength = 0;
        //用哈希表来记录字符和相应的位置
        Map<Character, Integer> map = new HashMap<>(s.length());
        //滑动窗口的起始坐标和结束坐标
        int start = 0, end;
        for (int i = 0; i < s.length(); i++) {
            end = i;
            char key = s.charAt(end);
            if (map.containsKey(key)) {
                //此处需要那最大的start，"abba"，当遍历第二个a的时候，map.get("a")就会被start小
                start = Math.max(map.get(key), start);
            }
            maxLength = Math.max(maxLength, end - start + 1);
            map.put(key, end + 1);
        }
        return maxLength;
    }

    /**
     * 该解法有问题：只考虑到连续重复的
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        if (null == s || "".equals(s)) {
            return maxLength;
        }
        //遍历字符串
        int counter = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {

            }
            counter++;
            set.add(s.charAt(i));
            maxLength = maxLength > set.size() ? maxLength : set.size();
            //如果二者长度不一致，则说明字符出现了重复
            if (set.size() < counter) {
                //重置所有数据
                set.clear();
                counter = 0;
                //从本次字符串重新开始进行统计
                set.add(s.charAt(i));
                counter++;
            }
        }
        return maxLength;
    }
}
