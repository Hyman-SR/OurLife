package com.ourlife.base.leetcode;

/**
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 *
 * @author zhangchao
 * @createdOn 2020/8/12
 */
public class _21_合并两个有序链表 {

    public static void main(String[] args) {
        _21_合并两个有序链表 o = new _21_合并两个有序链表();
        ListNode listNode = o.mergeTwoLists(ListNodeUtils.buildListNode(new int[]{1, 3, 5, 6}), ListNodeUtils.buildListNode(new int[]{2, 4, 7}));
        System.out.println(111);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (null == l1) {
            return l2;
        }
        if (null == l2) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
