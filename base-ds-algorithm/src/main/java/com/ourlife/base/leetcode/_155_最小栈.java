package com.ourlife.base.leetcode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/min-stack/
 *
 * @author zhangchao
 * @createdOn 2020/8/13
 */
public class _155_最小栈 {

    public static void main(String[] args) {
        Object peek = new Stack<>().peek();
        System.out.println(111);
    }
}

class MinStack_3 {
    class Node {
        int value;
        int min;
        Node next;

        Node(int x, int min) {
            this.value = x;
            this.min = min;
            next = null;
        }
    }
    Node head;
    //每次加入的节点放到头部
    public void push(int x) {
        if (null == head) {
            head = new Node(x, x);
        } else {
            //当前值和之前节点的最小值较小的作为当前的min
            Node node = new Node(x, Math.min(x, head.min));
            node.next = head;
            head = node;
        }
    }

    public void pop() {
        if (head != null) {
            head = head.next;
        }
    }

    public int top() {
        if (head != null) {
            return head.value;
        }
        return -1;
    }

    public int getMin() {
        if (null != head) {
            return head.min;
        }
        return -1;
    }
}

/**
 * 单栈实现
 */
class MinStack_2 {
    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<>();

    public void push(int x) {
        //当前值更小
        if (x <= min) {
            //此处存在问题，第一次push，会压入脏数据
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        if (stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}

/**
 * 双栈实现
 */
class MinStack_1 {

    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    /**
     * initialize your data structure here.
     */
    public MinStack_1() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (!minStack.isEmpty()) {
            int top = minStack.peek();
            //小于最小值才入栈
            if (x <= top) {
                minStack.push(x);
            }
        } else {
            minStack.push(x);
        }
    }

    public void pop() {
        int pop = stack.pop();
        if (!minStack.isEmpty()) {
            int top = minStack.peek();
            if (pop == top) {
                minStack.pop();
            }
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
