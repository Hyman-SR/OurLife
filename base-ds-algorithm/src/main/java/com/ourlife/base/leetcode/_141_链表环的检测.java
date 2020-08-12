package com.ourlife.base.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle/
 *
 * @author zhangchao
 * @createdOn 2020/8/12
 */
public class _141_链表环的检测 {

    public static void main(String[] args) {

    }

    public boolean hasCycle_快慢指针(ListNode head) {
        if (null == head || null == head.next) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            //如果快指针跑到链尾，说明是无环的
            if (null == fast || null == fast.next) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    public boolean hasCycle_哈希(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }
}
