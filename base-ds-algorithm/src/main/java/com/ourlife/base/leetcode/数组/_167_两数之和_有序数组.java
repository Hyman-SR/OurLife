package com.ourlife.base.leetcode.数组;

/**
 * https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
 *
 * @author zhangchao
 * @createdOn 2020/8/27
 */
public class _167_两数之和_有序数组 {


    public int[] towSum_双指针(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[]{low + 1, high + 1};
            } else if (sum < target) {
                low++;
            } else {
                high--;
            }
        }
        return new int[]{-1, -1};
    }

    public int[] twoSum_二分法(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int low = i + 1, high = numbers.length - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                int dif = target - numbers[i];
                if (numbers[mid] == dif) {
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[mid] > dif) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return new int[]{-1, -1};
    }
}
