package com.ourlife.base.leetcode.数组;

/**
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 *
 * @author zhangchao
 * @createdOn 2020/8/27
 */
public class _215_数组中第K个最大元素 {

    public static void main(String[] args) {
        _215_数组中第K个最大元素 o = new _215_数组中第K个最大元素();
        int kthLargest = o.findKthLargest_小顶堆(new int[]{1, 2, 3, 4, 5, 6}, 4);
        System.out.println(kthLargest);
    }

    public int findKthLargest_小顶堆(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
            swap(nums, 0, i);
            --heapSize;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    public void buildMaxHeap(int[] a, int heapSize) {
        for (int i = heapSize / 2; i >= 0; --i) {
            maxHeapify(a, i, heapSize);
        }
    }

    public void maxHeapify(int[] a, int i, int heapSize) {
        int l = i * 2 + 1, r = i * 2 + 2, largetst = i;
        if (l < heapSize && a[l] > a[largetst]) {
            largetst = l;
        }
        if (r < heapSize && a[r] > a[largetst]) {
            largetst = r;
        }
        if (largetst != i) {
            swap(a, i, largetst);
            maxHeapify(a, largetst, heapSize);
        }
    }


    /* =============================================================================================================================== */

    public int findKthLargest_快排(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    public int quickSelect(int[] a, int l, int r, int index) {
        int q = randomPartitiom(a, l, r);
        if (q == index) {
            return a[q];
        } else {
            if (q < index) {
                return quickSelect(a, q + 1, r, index);
            } else {
                return quickSelect(a, l, q - 1, index);
            }
        }
    }

    public int randomPartitiom(int[] a, int l, int r) {
        int pivot = (int) (Math.random() * (r - l + 1)) + l;
        swap(a, pivot, r);
        int smallIndex = l - 1;
        for (int i = l; i <= r; i++) {
            if (a[i] <= a[r]) {
                smallIndex++;
                if (i > smallIndex) {
                    swap(a, i, smallIndex);
                }
            }
        }
        return smallIndex;
    }


    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
