package com.annis.leetcode.simple;

import com.annis.leetcode.simple.model.ListNode;

/**
 * 需要再次做的
 */
public class AgainTest {
    public static void main(String[] args) {
        AgainTest test = new AgainTest();

        //反转链表
        test.reverseListTest();
    }

    private void reverseListTest() {
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        head1.next = head2;
        ListNode head3 = new ListNode(3);
        head2.next = head3;
        ListNode head4 = new ListNode(4);
        head3.next = head4;
//        ListNode head5 = new ListNode(5);
//        head4.next = head5;
        ListNode newHead = reverseList(head1);
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }

    /**
     * 反转链表
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     */
    public ListNode reverseList(ListNode head) {


        return null;
    }
}
