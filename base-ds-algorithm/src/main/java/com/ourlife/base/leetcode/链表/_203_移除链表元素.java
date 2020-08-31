package com.ourlife.base.leetcode.链表;

/**
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 * @author zhangchao
 * @createdOn 2020/8/26
 */
public class _203_移除链表元素 {

    /**
     * [1,2,6,3,4,5,6]
     * 双指针
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
       ListNode dummy = new ListNode(0);
       dummy.next = head;
       ListNode pre = dummy, cur = dummy.next;
       while (cur != null) {
           if (cur.val == val) {
               pre.next = cur.next;
           } else {
               pre = cur;
           }
           cur = cur.next;
       }
       return dummy.next;
    }
}
