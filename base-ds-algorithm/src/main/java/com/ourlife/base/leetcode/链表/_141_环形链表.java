package com.ourlife.base.leetcode.链表;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle/
 * @author zhangchao
 * @createdOn 2020/8/26
 */
public class _141_环形链表 {

    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (slow != null) {
            if (fast == null || fast.next == null) {
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
