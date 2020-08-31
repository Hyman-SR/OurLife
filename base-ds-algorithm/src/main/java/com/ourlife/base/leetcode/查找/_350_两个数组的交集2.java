package com.ourlife.base.leetcode.查找;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
 * @author zhangchao
 * @createdOn 2020/8/28
 */
public class _350_两个数组的交集2 {

    public static void main(String[] args) {
        _350_两个数组的交集2 o = new _350_两个数组的交集2();
        int[] arr = o.intersect(new int[]{1, 2, 2, 1}, new int[]{2, 2});
        System.out.println(arr);
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<>(nums1.length);
        for (int i = 0; i < nums1.length; i++) {
            if (map1.containsKey(nums1[i])) {
                map1.put(nums1[i], map1.get(nums1[i]) + 1);
            } else {
                map1.put(nums1[i], 1);
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (map1.containsKey(nums2[i]) && map1.get(nums2[i]) > 0) {
                list.add(nums2[i]);
                map1.put(nums2[i], map1.get(nums2[i]) - 1);
            }
        }
        int[] out = new int[list.size()];
        int idx = 0;
        for (int i : list) {
            out[idx++] = i;
        }
        return out;
    }
}
