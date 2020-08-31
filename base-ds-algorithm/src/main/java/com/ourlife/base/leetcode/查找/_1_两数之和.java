package com.ourlife.base.leetcode.查找;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/two-sum/
 *
 * @author zhangchao
 * @createdOn 2020/8/28
 */
public class _1_两数之和 {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int value = target - nums[i];
            if (map.containsKey(value)) {
                return new int[]{map.get(value), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }
}
