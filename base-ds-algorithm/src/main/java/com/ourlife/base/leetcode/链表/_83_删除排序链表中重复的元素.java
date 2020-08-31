package com.ourlife.base.leetcode.链表;

/**
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 *
 * @author zhangchao
 * @createdOn 2020/8/26
 */
public class _83_删除排序链表中重复的元素 {

    public ListNode deleteDuplicates(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (pre.val == cur.val) {
                pre.next = cur.next;
            } else {
                pre = pre.next;
            }
            cur = cur.next;
        }
        return head;
    }
}
