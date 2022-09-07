package ch02_ArrayDoublePointer.b_SingleListdeleteDuplicates;


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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            if (fast.val != slow.val) {
                //slow = slow.next;
                //slow.val = fast.val;
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }

        slow.next = null;

        return head;
    }
    //public ListNode deleteDuplicates(ListNode head) {
    //    if (head == null) {
    //        return null;
    //    }
    //    ListNode slow = head, fast = head;
    //    while (fast != null) {
    //        if (fast.val != slow.val) {
    //            slow = slow.next;
    //            slow.val = fast.val;
    //        }
    //        fast = fast.next;
    //    }
    //
    //    slow.next = null;
    //
    //    return head;
    //}
}