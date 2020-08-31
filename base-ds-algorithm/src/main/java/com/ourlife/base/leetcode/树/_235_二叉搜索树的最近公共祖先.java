package com.ourlife.base.leetcode.树;

/**
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * @author zhangchao
 * @createdOn 2020/8/29
 */
public class _235_二叉搜索树的最近公共祖先 {

    /**
     * 二叉搜索树满足 左节点的孩子都小于根节点，右节点的孩子都大于根节点
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        int parentVal = root.val, pVal = p.val, qVal = q.val;
        if (pVal < parentVal && q.val < parentVal) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (pVal > parentVal && qVal > parentVal) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }
}
