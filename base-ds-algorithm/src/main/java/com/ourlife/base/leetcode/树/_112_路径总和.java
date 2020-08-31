package com.ourlife.base.leetcode.树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/path-sum/
 *
 * @author zhangchao
 * @createdOn 2020/8/28
 */
public class _112_路径总和 {

    /**
     * 递归
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum_递归(TreeNode root, int sum) {
        if (null == root) {
            return false;
        }
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                return true;
            }
        }
        return hasPathSum_递归(root.left, sum - root.val) || hasPathSum_递归(root.right, sum - root.val);
    }

    /**
     * 广度遍历
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (null == root) {
            return false;
        }
        Queue<TreeNode> queNode = new LinkedList<>();
        Queue<Integer> queSum = new LinkedList<>();
        queNode.offer(root);
        queSum.offer(root.val);
        while (!queNode.isEmpty()) {
            TreeNode nowNode = queNode.poll();
            Integer nowSum = queSum.poll();
            //当前节点的左右孩子都为null，表示已经是叶子节点
            if (nowNode.left == null && nowNode.right == null) {
                if (nowSum == sum) {
                    return true;
                }
                continue;
            }
            //当前节点仍然有左孩子或右孩子时，则继续往下一层走
            if (nowNode.left != null) {
                queNode.offer(nowNode.left);
                queSum.offer(nowSum + nowNode.left.val);
            }
            if (nowNode.right != null) {
                queNode.offer(nowNode.right);
                queSum.offer(nowSum + nowNode.right.val);
            }
        }
        return false;
    }
}
