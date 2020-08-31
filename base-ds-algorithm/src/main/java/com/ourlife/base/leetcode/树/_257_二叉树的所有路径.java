package com.ourlife.base.leetcode.树;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/binary-tree-paths/
 *
 * @author zhangchao
 * @createdOn 2020/8/29
 */
public class _257_二叉树的所有路径 {

    /**
     * DFS 深度优先遍历
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        dfs(root, "", paths);
        return paths;
    }

    private void dfs(TreeNode root, String path, List<String> paths) {
        if (root != null) {
            path += root.val;
            if (root.left == null && root.right == null) {
                paths.add(path);
            } else {
                path += "->";
                dfs(root.left, path, paths);
                dfs(root.right, path, paths);
            }
        }
    }
}
