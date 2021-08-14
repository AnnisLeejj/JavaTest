package com.annis.leetcode.simple;

import com.annis.leetcode.simple.model.ListNode;

import java.util.Stack;

public class LinkTest {

    public static void main(String[] args) {
        LinkTest test = new LinkTest();

        //删除链表的倒数第N个节点
//        test.removeNthFromEndTest();
        //反转链表
//        test.reverseListTest();

        //合并两个有序链表
//        test.mergeTwoListsTest();
        //回文链表
        test.isPalindromeTest();
    }

    private void printLink(ListNode head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    /**
     * 回文链表
     * 请判断一个链表是否为回文链表。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->2
     * 输出: false
     * 示例 2:
     * <p>
     * 输入: 1->2->2->1
     * 输出: true
     * 进阶：
     * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     */
    public void isPalindromeTest() {
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        head1.next = head2;
        ListNode head3 = new ListNode(1);
        head2.next = head3;
//        ListNode head4 = new ListNode(2);
//        head3.next = head4;
//        ListNode head5 = new ListNode(1);
//        head4.next = head5;

        System.out.println(isPalindrome3(head1));
//        System.out.println(isPalindrome(null));
    }

    /*
    执行用时：4 ms, 在所有 Java 提交中击败了75.52%的用户
    内存消耗：48.4 MB, 在所有 Java 提交中击败了45.05%的用户
     */
    public boolean isPalindrome3(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            slow = slow.next;
        }

        ListNode newHead = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = newHead;
            newHead = slow;
            slow = next;
        }
        while (newHead != null) {
            if (head.val != newHead.val) {
                return false;
            }
            head = head.next;
            newHead = newHead.next;
        }
        return true;
    }

    public boolean isPalindrome2(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            stack.add(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            slow = slow.next;
        }
        while (slow != null) {
            if (stack.pop() != slow.val) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    /*
    执行用时：9 ms, 在所有 Java 提交中击败了33.32%的用户
    内存消耗：52.4 MB, 在所有 Java 提交中击败了12.01%的用户
     */
    public boolean isPalindrome(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            stack.add(slow);
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            slow = slow.next;
        }
        while (slow != null) {
            if (stack.pop().val != slow.val) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }


    /**
     * 合并两个有序链表
     * <p>
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
     * <p>
     * 示例 1：
     * 输入：l1 = [1,2,4], l2 = [1,3,4]
     * 输出：[1,1,2,3,4,4]
     * 示例 2：
     * <p>
     * 输入：l1 = [], l2 = []
     * 输出：[]
     * 示例 3：
     * <p>
     * 输入：l1 = [], l2 = [0]
     * 输出：[0]
     */
    private void mergeTwoListsTest() {
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(4);
        head1.next = head2;
        ListNode head3 = new ListNode(6);
        head2.next = head3;

        ListNode head21 = new ListNode(2);
        ListNode head22 = new ListNode(3);
        head21.next = head22;
        ListNode head23 = new ListNode(4);
        head22.next = head23;
        printLink(mergeTwoLists2(head1, head21));
    }

    /*执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：37.8 MB, 在所有 Java 提交中击败了61.55%的用户*/
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode node = result;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                node.next = l1;
                l1 = l1.next;
            } else if (l1.val > l2.val) {
                node.next = l2;
                l2 = l2.next;
            } else {
                node.next = l1;
                l1 = l1.next;
                node = node.next;
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }
        if (l1 != null) {
            node.next = l1;
        } else {
            node.next = l2;
        }
        return result.next;
    }

    /*执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：37.9 MB, 在所有 Java 提交中击败了47.61%的用户*/
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode result;
        if (l1.val < l2.val) {
            result = l1;
            l1 = l1.next;
        } else {
            result = l2;
            l2 = l2.next;
        }
        ListNode node = result;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                node.next = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }
        if (l1 == null) {
            node.next = l2;
        }
        if (l2 == null) {
            node.next = l1;
        }
        return result;
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
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return node;
    }

    public ListNode reverseList2(ListNode head) {
        ListNode perv = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = perv;
            perv = head;
            head = next;
        }
        return perv;
    }


    /**
     * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 要被删除的节点 。
     * 现有一个链表 -- head = [4,5,1,9]，它可以表示为:
     * <p>
     * 示例 1：
     * <p>
     * 输入：head = [4,5,1,9], node = 5
     * 输出：[4,1,9]
     * 解释：给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     * 示例 2：
     * <p>
     * 输入：head = [4,5,1,9], node = 1
     * 输出：[4,5,9]
     * 解释：给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
     *  
     * <p>
     * 提示：
     * 链表至少包含两个节点。
     * 链表中所有节点的值都是唯一的。
     * 给定的节点为非末尾节点并且一定是链表中的一个有效节点。
     * 不要从你的函数中返回任何结果。
     */

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 删除链表的倒数第N个节点
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * <p>
     * 进阶：你能尝试使用一趟扫描实现吗？
     * 提示：
     * <p>
     * 链表中结点的数目为 sz
     * 1 <= sz <= 30
     * 0 <= Node.val <= 100
     * 1 <= n <= sz
     */
    private void removeNthFromEndTest() {
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        head1.next = head2;
//        ListNode head3 = new ListNode(3);
//        head2.next = head3;
//        ListNode head4 = new ListNode(4);
//        head3.next = head4;
//        ListNode head5 = new ListNode(5);
//        head4.next = head5;
        ListNode newHead = removeNthFromEnd(head1, 1);

        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }

    /*执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：36.6 MB, 在所有 Java 提交中击败了20.72%的用户
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode current = head;
        ListNode targetLastNode = head;

        int count = 0;
        while (current != null) {
            current = current.next;
            count++;
            if (count > n + 1) {
                targetLastNode = targetLastNode.next;
            }
        }
        if (count < n) {
            return head;
        } else if (count == n) {
            return head.next;
        }
        deleteNext(targetLastNode);
        return head;
    }

    private void deleteNext(ListNode current) {
        if (current.next != null) {
            if (current.next.next == null) {
                current.next = null;
            } else {
                current.next.val = current.next.next.val;
                current.next.next = current.next.next.next;
            }
        }
    }
}