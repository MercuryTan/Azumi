package com.mercury.lab.linkedList;

/**
 * @author ：tx
 * @description：
 * @date ：Created in 2021/4/2 15:59
 * @modified By：
 * @version:
 */
public class SingleLinkedReverse {

    /**
     * 功能： 以head为头结点，反转链表。并返回最终的头结点 【反转整个单链表】
     *
     * @param head
     * @return
     */
    ListNode reverse(ListNode head) {
        if (head.next == null) {
            return head;
        }
        // 从head.next开始反转， 此时最后头节点就为最后一个节点
        ListNode last = reverse(head.next);

        // head.next就为head的下一个节点 tmp，由于上一步已经反转了，那么tmp.next就为head（反转后原本是指向null）
        head.next.next = head;
        head.next = null;

        return last;
    }


    ListNode successor = null; // 后驱节点

    /**
     * 功能： 以head为头结点，反转前n个节点
     *
     * @param head
     * @param n
     * @return
     */
    ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }

        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;

        return last;
    }

    ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == 1) {
            return reverseN(head, n);
        }
        return reverseBetween(head.next, m - 1, n - 1);
    }

}

// 单链表节点的结构
class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}