package com.ourlife.base.leetcode.栈和队列;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 * @author zhangchao
 * @createdOn 2020/8/30
 */
public class _20_有效的括号 {

    /**
     * 只包含 ([{}])  采用用以下方式
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>(3);
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        Stack<Character> stack = new Stack();
        for (char c : s.toCharArray()) {
            //如果map包含做括号，则把右括号放入栈,  ([{}])
            if (map.containsKey(c)) {
                stack.push(map.get(c));
            } else if (stack.isEmpty() || c != stack.pop()) {
                return false;
            }
        }

        //遍历完，如果stack还有值，则说明不合法
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }
}
