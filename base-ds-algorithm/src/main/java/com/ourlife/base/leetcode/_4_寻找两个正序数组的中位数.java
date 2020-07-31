package com.ourlife.base.leetcode;

import com.ourlife.base.common.util.SortUtils;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 *
 * 太难了，先放弃了
 *
 * @author zhangchao
 * @createdOn 2020/7/22
 */
public class _4_寻找两个正序数组的中位数 {

    public static void main(String[] args) {
        _4_寻找两个正序数组的中位数 o = new _4_寻找两个正序数组的中位数();
        int[] nums1 = new int[]{3, 4, 2, 1, 89};
        int[] nums2 = new int[]{1, 2, 3, 0};
        System.out.println(Arrays.toString(SortUtils.merge(nums1, nums2)));
    }


    /**
     * 此为暴力解发：先合并两个数组并排序，然后去中位数
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //使用归并排序合并两个数组

        //获取中间位数
        return 0;
    }

}
