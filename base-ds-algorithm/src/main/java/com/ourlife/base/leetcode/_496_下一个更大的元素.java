package com.ourlife.base.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/next-greater-element-i/
 *
 * @author zhangchao
 * @createdOn 2020/8/13
 */
public class _496_下一个更大的元素 {

    public static void main(String[] args) {
        _496_下一个更大的元素 o = new _496_下一个更大的元素();
        System.out.println(Arrays.toString(o.nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2})));
    }

    /**
     * 用单调栈找到所有元素中下一个更大的元素，用哈希表来记录
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>(nums2.length);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                map.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }
        int[] arrs = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            arrs[i] = map.get(nums1[i]) == null ? -1 : map.get(nums1[i]);
        }
        return arrs;
    }
}
