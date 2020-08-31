package com.ourlife.base.dataStructure.stack;

/**
 * 用数组实现一个顺序栈
 *
 * @author zhangchao
 * @createdOn 2020/8/13
 */
public class ArrayStack {
    /**
     * 数组
     */
    private String[] items;
    /**
     * 栈中元素个数
     */
    private int count;
    /**
     * 栈的大小
     */
    private int n;

    public ArrayStack(int n) {
        this.items = new String[n];
        this.n = n;
        this.count = 0;
    }

    public boolean push(String item) {
        //栈慢，则入栈失败
        if (count == n) {
            return false;
        }
        items[count] = item;
        ++count;
        return true;
    }

    public String pop() {
        if (count == 0) {
            return null;
        }
        String tmp = items[count - 1];
        --count;
        return tmp;
    }

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(10);
        for (int i = 0; i < 5; i++) {
            arrayStack.push((i + 1) + "");
        }
        System.out.println(Integer.parseInt(arrayStack.pop()) + 1 == 6);
    }

}
