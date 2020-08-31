package com.ourlife.base.leetcode.数组;

import com.ourlife.base.common.util.SortUtils;

import java.util.Random;

/**
 * @author zhangchao
 * @createdOn 2020/8/27
 */
public class 快排算法 {
    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 4, 5, 7, 2, 3};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(111);
    }

    /**
     * 确定一个基准位 -> 将数组元素 小于基准位元素和大于基准位元素的元素分别放置基准位两边 -> 递归操作 -> left = right = 基准位 截止
     *
     * @param arr
     * @param start
     * @param end
     */
    public static void quickSort(int[] arr, int start, int end) {
        if (arr.length < 1 || start < 0 || end >= arr.length || start > end) {
            return;
        }
        int smallIndex = partition(arr, start, end);
        if (start < smallIndex) {
            quickSort(arr, start, smallIndex - 1);
        }
        if (end > smallIndex) {
            quickSort(arr, smallIndex + 1, end);
        }
    }

    private static int partition(int[] arr, int start, int end) {
        //获取基准数位置
        int pivot = (int) (start + Math.random() * (end - start + 1));
        //初始一个第一次交换位置的前一位
        int smallIndex = start - 1;
        //将基准数置换到数组最后边
        swap(arr, pivot, end);
        for (int i = start; i <= end; i++) {
            //当前数小于等于基准数时，将smallIndex右移一位
            if (arr[i] <= arr[end]) {
                smallIndex++;
                //如果当前位置在smallIndex位置之后，则交换两个位置
                if (i > smallIndex) {
                    swap(arr, i, smallIndex);
                }
            }
        }
        return smallIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
