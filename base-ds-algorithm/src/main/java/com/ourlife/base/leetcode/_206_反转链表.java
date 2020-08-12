package com.ourlife.base.leetcode;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * @author zhangchao
 * @createdOn 2020/8/12
 */
public class _206_反转链表 {

    public static void main(String[] args) {
        _206_反转链表 o = new _206_反转链表();
        ListNode listNode = ListNodeUtils.buildListNode(new int[]{1, 2, 3, 4, 5});
//        ListNode reverseListNode = o.reverseListByStack(listNode);
        ListNode reverseListNode = o.reverseList(listNode);
        System.out.println(111);
    }

    /**
     * 迭代
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        //源链表的前一个节点
        ListNode pre = null;
        //当前节点
        ListNode cur = head;
        while (cur != null) {
            //记录源链表的下一个节点
            ListNode nextNodeTemp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextNodeTemp;
        }
        return pre;
    }

    /**
     * 压栈
     *
     * @param head
     * @return
     */
    public ListNode reverseListByStack(ListNode head) {
//        1-2-3-4-5
        if (null == head) {
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        ListNode newHead = stack.pop();
        ListNode newHeadCopy = newHead;
        while (!stack.isEmpty()) {
            newHeadCopy.next = stack.pop();
            newHeadCopy = newHeadCopy.next;
        }
        newHeadCopy.next = null;
        return newHead;
    }
}

class ListNodeUtils {

    public static ListNode buildListNode(int[] arrs) {
        ListNode listNode = new ListNode(arrs[0]);
        ListNode listNodeCopy = listNode;
        int i = 1;
        while (i < arrs.length) {
            listNode.next = new ListNode(arrs[i]);
            listNode = listNode.next;
            i++;
        }
        return listNodeCopy;
    }
}
