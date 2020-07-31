package com.ourlife.base.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和 ：https://leetcode-cn.com/problems/two-sum/
 *
 * @author zhangchao
 * @createdOn 2020/7/21
 */
public class _1_两数之和 {

    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        int target = 6;
        System.out.println(Arrays.toString(new _1_两数之和().twoSum(nums, target)));
        System.out.println(Arrays.toString(new _1_两数之和().twoSum_精选(nums, target)));

    }

    /**
     * 哈希映射
     * 哈希查找时间复杂度为O(1)，可利用哈希容器map降低时间复杂度
     * 时间复杂度O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum_精选(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int value = target - nums[i];
            if (map.containsKey(value)) {
                return new int[] {map.get(value), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 暴力解
     * 时间复杂度O(n^2)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        /*
        //将数字进行排序  : 注，不能私自进行排序，排序之后会跟之前数组下标不一致，导致用例不能通过，pia，给一巴掌
        sortAsc(nums);
        for (int i = 0; i < nums.length; i++) {
            int value = target - nums[i];
            if (target - nums[i] > nums[nums.length - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == value) {
                    return new int[]{i, j};
                }
            }
        }
        */
        for (int i = 0; i < nums.length; i++) {
            int value = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == value) {
                    return new int[]{i, j};
                }
            }
        }

        throw new IllegalArgumentException("No two sum solution");
    }

    private int[] sortAsc(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    nums[j] = nums[j] ^ nums[j + 1];
                    nums[j + 1] = nums[j] ^ nums[j + 1];
                    nums[j] = nums[j] ^ nums[j + 1];
                }
            }
        }
        return nums;
    }
}
