package com.ourlife.base.leetcode.数组;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://leetcode-cn.com/problems/sort-colors/
 *
 * @author zhangchao
 * @createdOn 2020/8/26
 */
public class _75_颜色分类 {

    public static void main(String[] args) {
        _75_颜色分类 o = new _75_颜色分类();
//        o.sortColors(new int[]{2, 0, 2, 1, 1, 0});
//        o.sortColors_1(new int[]{2, 0, 2, 1, 1, 0});
        o.sortColors_1(new int[]{1, 2, 0});
    }

    /**
     * 一次遍历的解法
     * 有 0、1、2三种颜色，定义最左边0的左边界和最右边2的有边界
     *
     * @param nums
     */
    public void sortColors_1(int[] nums) {
        int cur = 0, p0 = 0, p2 = nums.length - 1;
        while (cur <= p2) {
            if (nums[cur] == 2) {
                swap(nums, cur, p2);
                p2--;
            } else if (nums[cur] == 0) {
                swap(nums, cur, p0);
                cur++;
                p0++;
            } else {
                cur++;
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        if (a == b || nums[a] == nums[b]) {
            return;
        }
        nums[a] = nums[a] ^ nums[b];
        nums[b] = nums[a] ^ nums[b];
        nums[a] = nums[a] ^ nums[b];
    }

    /**
     * 两次遍历
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        int count_0 = 0, count_1 = 0, count_2 = 0;
        for (int i = 0; i < nums.length; i++) {
            switch (nums[i]) {
                case 0:
                    count_0++;
                    break;
                case 1:
                    count_1++;
                    break;
                case 2:
                    count_2++;
                    break;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i < count_0) {
                nums[i] = 0;
            } else if (i < count_0 + count_1) {
                nums[i] = 1;
            } else {
                nums[i] = 2;
            }
        }
    }
}
