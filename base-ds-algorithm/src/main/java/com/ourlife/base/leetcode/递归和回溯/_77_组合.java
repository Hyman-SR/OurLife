package com.ourlife.base.leetcode.递归和回溯;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/combinations/
 *
 * @author zhangchao
 * @createdOn 2020/8/31
 */
public class _77_组合 {

    public static void main(String[] args) {
        _77_组合 o = new _77_组合();
        List<List<Integer>> combine = o.combine(5, 2);
        System.out.println(combine);
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 0 || k <= 0 || n < k) {
            return res;
        }
        dfs(n, k, 1, new Stack<>(), res);
        return res;
    }

    private void dfs(int n, int k, int begin, Stack<Integer> pre, List<List<Integer>> res) {
        if (pre.size() == k) {
            res.add(new ArrayList<>(pre));
            return;
        }
        for (int i = begin; i <= n; i++) {
            pre.add(i);
            dfs(n, k, i + 1, pre, res);
            pre.pop();
        }
    }
}
