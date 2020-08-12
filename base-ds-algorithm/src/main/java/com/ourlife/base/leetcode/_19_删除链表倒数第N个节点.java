package com.ourlife.base.leetcode;

/**
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 * @author zhangchao
 * @createdOn 2020/8/12
 */
public class _19_删除链表倒数第N个节点 {

    public static void main(String[] args) {
        _19_删除链表倒数第N个节点 o = new _19_删除链表倒数第N个节点();
        ListNode listNode = o.removeNthFromEnd(ListNodeUtils.buildListNode(new int[]{1}), 1);
        System.out.println(111);
    }

    /**
     * 双指针定位，定义两个指针a，b，保持a始终比b快n个节点，a到达链尾时候，b就在倒数第N个节点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 1->2->3->4->5  ==> 0->1->2->3->4->5
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        //将fast指针移动到head的第N+1个位置，在dummy的第N+2的位置，头节点是一个哑结点
        for (int i = 1; i <= n + 1; i++) {
            fast = fast.next;
        }
        //此时。fast在dummy的第N+2的位置，在head的第N+1个位置
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        //当fast走完所有链表后，移除slow的下一个节点，即倒数第N个节点
        slow.next = slow.next.next;
        return dummy.next;
    }
}
