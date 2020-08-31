package com.ourlife.base.leetcode.数组;

/**
 * https://leetcode-cn.com/problems/move-zeroes/
 *
 * @author zhangchao
 * @createdOn 2020/8/26
 */
public class _283_移动零 {

    public static void main(String[] args) {
        _283_移动零 o = new _283_移动零();
        o.moveZeroes(new int[]{0, 1, 0, 3, 12});
    }

    /**
     * 倒序排一次就完事，O(n^2)？
     * 遍历一次，去做置换？
     * 都不对，不能改变数组原先非零元素的顺序
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int zeroCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            } else {
                nums[i - zeroCount] = nums[i];
            }
        }
        while (zeroCount > 0) {
            nums[nums.length - zeroCount] = 0;
            zeroCount--;
        }
    }
}
