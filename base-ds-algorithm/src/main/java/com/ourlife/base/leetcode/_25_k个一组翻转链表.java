package com.ourlife.base.leetcode;

/**
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 *
 * @author zhangchao
 * @createdOn 2020/8/17
 */
public class _25_k个一组翻转链表 {

    public static void main(String[] args) {
        _25_k个一组翻转链表 o = new _25_k个一组翻转链表();
        ListNode listNode = ListNodeUtils.buildListNode(new int[]{1, 2, 3, 4, 5});
        o.reverse(listNode);
    }

    /**
     * 迭代   1-2-3-4-5   ==> k=2  2-1-4-3-5
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode end = dummy;
        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;

            end = pre;
        }
        return dummy.next;
    }


    /**
     * 1->2->3->4->5->null  ==>  null<-1<-2<-3<-4<-5
     *
     * @param head
     * @return
     */
    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (null != cur) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
