package com.ourlife.base.leetcode.栈和队列;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 *
 * @author zhangchao
 * @createdOn 2020/8/31
 */
public class _144_二叉树的前序遍历 {

    /**
     * BFS实现前序，但不是真正的前序，只是结果一致    根 -> 左 -> 右
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal_BFS(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pollLast();
            output.add(pop.val);
            //要先压右孩子
            if (pop.right != null) {
                stack.add(pop.right);
            }
            //再压左孩子
            if (pop.left != null) {
                stack.add(pop.left);
            }
        }
        return output;
    }

    /**
     * 前序遍历  根 -> 左 -> 右
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorderTraversal(root, list);
        return list;
    }

    private void preorderTraversal(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            preorderTraversal(root.left, list);
            preorderTraversal(root.right, list);
        }
    }

    /**
     * 中序遍历用DFS   左 -> 根 -> 右
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderTraversal(root, list);
        return list;
    }

    private void inorderTraversal(TreeNode root, List<Integer> list) {
        if (root != null) {
            inorderTraversal(root.left, list);
            list.add(root.val);
            inorderTraversal(root.right, list);
        }
    }

    /**
     * 后序遍历用DFS   左 -> 右 -> 根
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorderTraversal(root, list);
        return list;
    }

    private void postorderTraversal(TreeNode root, List<Integer> list) {
        if (root != null) {
            postorderTraversal(root.left, list);
            postorderTraversal(root.right, list);
            list.add(root.val);
        }
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