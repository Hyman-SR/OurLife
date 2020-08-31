package com.ourlife.base.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 * @author zhangchao
 * @createdOn 2020/8/13
 */
public class _20_有效的括号 {

    public static void main(String[] args) {
        _20_有效的括号 o = new _20_有效的括号();
        System.out.println(o.isValid("()[]{}"));

    }

    public boolean isValid_精选(String s) {
        if (s.isEmpty()) {
            return true;
        }
        Map<Character, Character> map =  new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                stack.push(map.get(c));
            } else if(stack.empty() || c != stack.pop()) {
                return false;
            }
        }
        if (stack.empty()) {
            return true;
        }
        return false;
    }

    /**
     * 过于繁琐
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map =  new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
               if (!stack.contains(map.get(c))) {
                   return false;
               } else {
                   char c1 = stack.pop().charValue();
                   if (c1 != map.get(c)) {
                       return false;
                   }
               }
            } else {
                stack.push(c);
            }
        }
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }

}
