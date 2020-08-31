package com.ourlife.base.leetcode.递归和回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/permutations/
 *
 * @author zhangchao
 * @createdOn 2020/8/31
 */
public class _46_全排列 {

    /**
     * DFS 递归参数  原始数组、数组长度、当前深度、当前排列元素集合、全排列元素集合
     * 终止条件
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }

        boolean[] used = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();
        dfs(nums, nums.length, 0, used, path, res);
        return res;
    }

    private void dfs(int[] nums, int length, int depth, boolean[] used, List<Integer> path, List<List<Integer>> res) {
        if (length == depth) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < length; i++) {
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;
                dfs(nums, length, depth + 1, used, path, res);

                //状态重置
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}
