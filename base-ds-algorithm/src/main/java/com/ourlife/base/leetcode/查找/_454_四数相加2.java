package com.ourlife.base.leetcode.查找;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/4sum-ii/
 *
 * @author zhangchao
 * @createdOn 2020/8/28
 */
public class _454_四数相加2 {

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int sumAB = A[i] + B[j];
                if (map.containsKey(sumAB)) {
                    map.put(sumAB, map.get(sumAB) + 1);
                } else {
                    map.put(sumAB, 1);
                }
            }
        }

        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int sumCD = -(C[i] + D[j]);
                if (map.containsKey(sumCD)) {
                    res += map.get(sumCD);
                }
            }
        }

        return res;
    }
}
