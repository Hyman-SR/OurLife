package com.ourlife.base.leetcode.数组;

/**
 * https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/
 * @author zhangchao
 * @createdOn 2020/8/25
 */
public class _674_最长连续递增序列 {

    public static void main(String[] args) {
        _674_最长连续递增序列 o = new _674_最长连续递增序列();
        System.out.println(o.findLengthOfLCIS(new int[]{1, 3, 5, 4, 7}));
    }

    /**
     * 滑动窗口
     *
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        int start = 0, maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] <= nums[i - 1]) {
                start = i;
            }
            maxLength = Math.max(maxLength, i - start + 1);
        }
        return maxLength;
    }
}
