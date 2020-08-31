package com.ourlife.base.leetcode.栈和队列;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 * @author zhangchao
 * @createdOn 2020/8/31
 */
public class _102_二叉树的层序遍历 {

    /**
     * BFS
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (null == root) {
            return list;
        }
        queue.offer(root);

        int curLevelSize = 0;
        List<TreeNode> curLevelNodes;
        List<Integer> curLevelNodeVals;
        while (!queue.isEmpty()) {
            //拿出当前层所有的节点并加入到当前层的集合
            curLevelSize = queue.size();
            curLevelNodes = new ArrayList<>(curLevelSize);
            curLevelNodeVals = new ArrayList<>(curLevelSize);
            for (int i = 0; i < curLevelSize; i++) {
                TreeNode poll = queue.poll();
                curLevelNodes.add(poll);
                curLevelNodeVals.add(poll.val);
            }
            list.add(curLevelNodeVals);
            //将当前层的下一层孩子加入到队列
            for (TreeNode node : curLevelNodes) {
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return list;
    }
}
