package ch01_SingleLinkedList.g_hasCycle;
/**
 * @Author mapKey
 * @Date 7/19/2022 10:10 AM
 * @Since version-1.0
 * @Description
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * 141. 环形链表
 * 142. 环形链表 II
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。不允许修改 链表。
 */

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Solution {
    //public boolean hasCycle(ListNode head) {
    //    ListNode slow = head, fast = head;
    //    while (fast != null && fast.next != null) {
    //        slow = slow.next;
    //        fast = fast.next.next;
    //        if (slow == fast) {
    //            return true;
    //        }
    //    }
    //    return false;
    //}
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }


        return slow;
    }
}