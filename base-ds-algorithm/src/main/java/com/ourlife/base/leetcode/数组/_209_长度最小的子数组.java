package com.ourlife.base.leetcode.数组;

/**
 * https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 * @author zhangchao
 * @createdOn 2020/8/27
 */
public class _209_长度最小的子数组 {

    public static void main(String[] args) {
        _209_长度最小的子数组 o = new _209_长度最小的子数组();
        int i = o.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3});
        System.out.println(111);
    }

    public int minSubArrayLen(int s, int[] nums) {
        int minLength = Integer.MAX_VALUE, start = 0, end, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            end = i;
            sum += nums[i];
            while (sum >= s && start <= end) {
                minLength = Math.min(minLength, end - start + 1);
                //缩小start 和 end的范围，sum减去start位置元素，并将start右移
                sum -= nums[start];
                start++;
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

}
