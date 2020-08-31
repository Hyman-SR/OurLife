package com.ourlife.base.leetcode.数组;

/**
 * https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof/
 *
 * @author zhangchao
 * @createdOn 2020/8/25
 */
public class _剑指of53_0到n减1中缺失的数字 {

    public int missingNumber(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            if (nums[m] == m) {
                i = m + 1;
            } else {
                j = m - 1;
            }
        }
        return i;
    }
}
