package com.ourlife.base.dataStructure.queue;

/**
 * 数组实现队列会有一个数据搬移问题，性能相对链表差一些
 *
 * @author zhangchao
 * @createdOn 2020/8/19
 */
public class ArrayQueue {

    private String[] items;
    private int n = 0;
    /**
     * 队头
     */
    private int head = 0;
    /**
     * 队尾
     */
    private int tail = 0;

    public ArrayQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    /**
     * 入队，当tail达到数组最大长度，会导致队列无法入队
     *
     * @param item
     * @return
     */
    public boolean enqueue(String item) {
        if (tail == n) {
            return false;
        }
        items[tail] = item;
        ++tail;
        return true;
    }

    public boolean enqueue_优化(String item) {
        //tail指针到达数组长度时，判断head是否在0位置，如果不在，则说明数组未满，则移动
        if (tail == n) {
            if (head == 0) {
                return false;
            }
            for (int i = 0; i < tail - head; i++) {
                items[i] = items[i + head];
            }
            //重置两个指针
            head = 0;
            tail -= head;
        }
        items[tail] = item;
        ++tail;
        return true;
    }

    /**
     * 出队
     *
     * @return
     */
    public String dequeue() {
        //队列为空
        if (head == tail) {
            return null;
        }
        String ret = items[head];
        ++head;
        return ret;
    }
}
