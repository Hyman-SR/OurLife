package com.ourlife.base.leetcode.数组;

/**
 * https://leetcode-cn.com/problems/search-insert-position/
 * @author zhangchao
 * @createdOn 2020/8/25
 */
public class _35_搜索插入位置 {

    public int searchInsert(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            if (nums[m] == target) {
                return m;
            } else if (nums[m] > target) {
                j = m - 1;
            } else {
                i = m + 1;
            }
        }
        return i;
    }
}
