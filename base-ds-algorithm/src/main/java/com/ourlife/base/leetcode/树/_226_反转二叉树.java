package com.ourlife.base.leetcode.树;

/**
 * https://leetcode-cn.com/problems/invert-binary-tree/
 *
 * @author zhangchao
 * @createdOn 2020/8/28
 */
public class _226_反转二叉树 {

    public TreeNode invertTree(TreeNode root) {
        if (null == root) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
