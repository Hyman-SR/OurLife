package com.ourlife.base.leetcode.树;

/**
 * https://leetcode-cn.com/problems/symmetric-tree/
 *
 * @author zhangchao
 * @createdOn 2020/8/28
 */
public class _101_对称二叉树 {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode left, TreeNode right) {
        //如果左节点和右节点都为null则也对称
        if (null == left && null == right) {
            return true;
        }
        //如果左右节点只有一个为null，则不对称
        if (null == left || null == right) {
            return false;
        }
        //如果左右节点的值不相等，则不对称
        if (left.val != right.val) {
            return false;
        }
        //继续对比 左节点的左孩子 和 右节点的右孩子 && 左节点的右孩子 和 右节点的左孩子
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }

}
