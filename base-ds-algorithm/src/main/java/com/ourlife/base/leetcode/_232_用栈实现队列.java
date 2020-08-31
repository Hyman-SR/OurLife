package com.ourlife.base.leetcode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 * @author zhangchao
 * @createdOn 2020/8/13
 */
public class _232_用栈实现队列 {

    public static void main(String[] args) {

    }
}

class MyQueue {

    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    /** Initialize your data structure here. */
    public MyQueue() {

    }

    /** Push element x to the back of stack. */
    public void push(int x) {
        stack1.push(x);
    }

    /** Removes the element from in front of stack and returns that element. */
    public int pop() {
        if (!stack2.isEmpty()) {
            return stack2.pop();
        }
        if (stack1.isEmpty()) {
            return -1;
        }
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (!stack2.isEmpty()) {
            return stack2.peek();
        }
        if (stack1.isEmpty()) {
            return -1;
        }
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        return stack2.isEmpty() ? -1 : stack2.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
