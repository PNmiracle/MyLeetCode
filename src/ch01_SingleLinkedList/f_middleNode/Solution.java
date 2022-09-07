package ch01_SingleLinkedList.f_middleNode;
/**
 * @Author mapKey
 * @Date 7/19/2022 9:52 AM
 * @Since version-1.0
 * @Description 
 * 876. 链表的中间结点
 * Given the head of a singly linked list, return the middle node of the linked list.
 *
 * If there are two middle nodes, return the second middle node.
 *
 */

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        //while (fast != null && fast.next != null && fast.next.next != null) {
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }
}
