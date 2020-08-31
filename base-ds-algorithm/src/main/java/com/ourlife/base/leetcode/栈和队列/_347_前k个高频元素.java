package com.ourlife.base.leetcode.栈和队列;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 *
 * @author zhangchao
 * @createdOn 2020/8/31
 */
public class _347_前k个高频元素 {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        //遍历map，用最小堆保存频率最大的k的元素
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });
        for (Integer key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
            } else if (map.get(key) > map.get(pq.peek())) {
                pq.remove();
                pq.add(key);
            }
        }
        //取出最小堆中的元素
        int[] arr = new int[k];
        int idx = 0;
        while (!pq.isEmpty()) {
            arr[idx++] = pq.remove();
        }
        return arr;
    }
}
