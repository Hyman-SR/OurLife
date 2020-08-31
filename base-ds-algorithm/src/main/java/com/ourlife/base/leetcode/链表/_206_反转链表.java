package com.ourlife.base.leetcode.链表;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 *
 * @author zhangchao
 * @createdOn 2020/8/26
 */
public class _206_反转链表 {

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
