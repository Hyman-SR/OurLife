package com.ourlife.base.leetcode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/backspace-string-compare/
 *
 * @author zhangchao
 * @createdOn 2020/8/13
 */
public class _844_比较含退格的字符串 {

    public static void main(String[] args) {
        _844_比较含退格的字符串 o = new _844_比较含退格的字符串();
        System.out.println(o.backspaceCompare("ab#c", "ad#c"));
    }

    public boolean backspaceCompare(String S, String T) {
        return getValue(S).equals(getValue(T));
    }

    private String getValue(String str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if ('#' == c) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}

