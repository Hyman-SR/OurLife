package com.ourlife.base.leetcode;

import java.util.Stack;

/**
 * 两数相加：https://leetcode-cn.com/problems/add-two-numbers/
 *
 * @author zhangchao
 * @createdOn 2020/7/21
 */
public class _2_两数相加 {

    public static void main(String[] args) {
        _2_两数相加 o = new _2_两数相加();
        ListNode l1 = o.buildListNode(new int[]{2, 4, 5});
        ListNode l2 = o.buildListNode(new int[]{5, 6, 4});
        ListNode listNode = o.addTwoNumbers_精选(l1, l2);
        System.out.println(111);
    }

    /**
     * 两个链表相加
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers_精选(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int value1 = l1 == null ? 0 : l1.val;
            int value2 = l2 == null ? 0 : l2.val;
            int sum = value1 + value2 + carry;
            int value = sum % 10;
            carry = sum / 10;
            cur.next = new ListNode(value);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        //两个链表的最后一位相=10的时候，要考虑末尾情况
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }

    /**
     * 该解法存在以下问题： 代码已被修改的不用再用了
     * 1.直接相加可能出现int溢出
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //将链表反转，从后往前计算
        int valueL1 = getValue(listNode2Stack(l1));
        int valueL2 = getValue(listNode2Stack(l2));
        // 342 + 465 = 807  ==> 708
        int value = valueL1 + valueL2;
        String str = value + "";
        int[] array = new int[str.length()];
        for (int i = str.length() - 1; i >= 0; i--) {
            String s = str.charAt(i) + "";
            array[str.length() - i - 1] = Integer.parseInt(s);
        }
        return buildListNode(array);
    }


    private ListNode buildListNode(int[] nodes) {
        ListNode preNode = new ListNode(0);
        ListNode nextNode = preNode;
        for (int i = 0; i < nodes.length; i++) {
           nextNode.next = new ListNode(nodes[i]);
           nextNode = nextNode.next;
        }
        return preNode.next;
    }

    private int getValue(Stack stack) {
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return Integer.parseInt(sb.toString());
    }

    private Stack listNode2Stack(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        return stack;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
