package com.ourlife.base.leetcode.查找;


import java.util.*;

/**
 * https://leetcode-cn.com/problems/intersection-of-two-arrays/
 * @author zhangchao
 * @createdOn 2020/8/28
 */
public class _349_两个数组的交集1 {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>(nums1.length);
        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }
        Set<Integer> set2 = new HashSet<>(nums2.length);
        for (int i = 0; i < nums2.length; i++) {
            if (set1.contains(nums2[i])) {
                set2.add(nums2[i]);
            }
        }
        int[] out = new int[set2.size()];
        int idx = 0;
        for (int i : set2) {
            out[idx++] = i;
        }
        return out;
    }
}
