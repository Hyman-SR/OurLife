package com.ourlife.base.leetcode;

/**
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 * @author zhangchao
 * @createdOn 2020/7/31
 */
public class _236_二叉树的最近公共祖先 {

    public static void main(String[] args) {

    }

    /**
     * 通过
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //终止条件
        if (root == null || root.equals(p) || root.equals(q)) {
            return root;
        }
        //获取左子树中满足条件的节点，都不满足则返回null
        TreeNode l = lowestCommonAncestor(root.left, p, q);
        //获取右子树中满足条件的节点，都不满足则返回null
        TreeNode r = lowestCommonAncestor(root.right, p, q);
        //如果l r都为null，则说明该节点已到达根部且都不满足
        if (l == null) {
            return r;
        }
        if (r == null) {
            return l;
        }
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
