package com.ourlife.base.leetcode.查找;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/contains-duplicate-ii/
 * @author zhangchao
 * @createdOn 2020/8/28
 */
public class _219_存在重复元素2 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && (i - map.get(nums[i]) <= k)) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }
}
