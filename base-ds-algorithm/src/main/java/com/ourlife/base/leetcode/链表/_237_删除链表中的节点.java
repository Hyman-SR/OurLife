package com.ourlife.base.leetcode.链表;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 * @author zhangchao
 * @createdOn 2020/8/26
 */
public class _237_删除链表中的节点 {

    /**
     * 替换节点的值，删除后一个节点
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
