package com.ourlife.base.leetcode.递归和回溯;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 *
 * @author zhangchao
 * @createdOn 2020/8/31
 */
public class _17_电话号码的字母组合 {

    public static void main(String[] args) {
        _17_电话号码的字母组合 o = new _17_电话号码的字母组合();
        List<String> list = o.letterCombinations("23");
        System.out.println(list);
    }

    Map<Integer, String> map = new HashMap<>();
    public List<String> letterCombinations(String digits) {
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");

        List<String> result = new ArrayList<>();
        if ("".equals(digits)) {
            return result;
        }
        findCombination(digits, 0, new StringBuilder(), result);
        return result;
    }

    private void findCombination(String digits, int index, StringBuilder s, List<String> result) {
        if (index == digits.length()) {
            result.add(s.toString());
            return;
        }

        char c = digits.charAt(index);
        String str = map.get(c - '0');
        for (int i = 0; i < str.length(); i++) {
            findCombination(digits, index + 1, s.append(str.charAt(i)), result);
            //清除掉对s的更改
            s.deleteCharAt(s.length() - 1);
        }
    }

}
