package com.ourlife.base.leetcode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/baseball-game/
 *
 * @author zhangchao
 * @createdOn 2020/8/13
 */
public class _682_棒球比赛 {
    public static void main(String[] args) {
        _682_棒球比赛 o = new _682_棒球比赛();
        System.out.println(o.calPoints(new String[]{"5", "2", "C", "D", "+"}));
    }

    public int calPoints(String[] ops) {
        int num = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < ops.length; i++) {
            if ("C".equals(ops[i])) {
                stack.pop();
            } else if ("D".equals(ops[i])) {
                stack.push(stack.peek() + stack.peek());
            } else if ("+".equals(ops[i])) {
                //获取前两轮的值
                Integer first = stack.pop();
                Integer second = stack.peek();
                stack.push(first);
                stack.push(first + second);
            } else {
                stack.push(Integer.valueOf(ops[i]));
            }
        }
        while (!stack.isEmpty()) {
            num += stack.pop();
        }
        return num;
    }

}
