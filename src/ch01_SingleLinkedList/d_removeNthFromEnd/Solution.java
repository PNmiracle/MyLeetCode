package ch01_SingleLinkedList.d_removeNthFromEnd;

/**
 * @Author mapKey
 * @Date 7/19/2022 9:19 AM
 * @Since version-1.0
 * @Description 查找倒数第k个结点
 * 19. 删除链表的倒数第 N 个结点
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

//class Solution {
//    public ListNode removeNthFromEnd(ListNode head, int k) {
//        ListNode p1 = head;
//        //p1走了k步
//        for (int i = 0; i < k; i++) {
//            p1 = p1.next;
//        }
//        ListNode p2 = head;
//        ListNode p2Pre = p2;
//        //p1,p2同时走n - k 步
//        while (p1 != null) {
//            p2Pre = p2;
//            p2 = p2.next;
//            p1 = p1.next;
//        }
//        //p2指向第n - k + 1个结点,即为倒数第k个结点
//        p2Pre.next = p2.next;
//        p2.next = null;
//        p2 = null;
//        return p2 == head ? p2.next : head;
//    }
//}
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //新增虚拟头结点,避免空指针,
        //极端情况: 只有一个结点,要删除倒数第一个结点,则没有倒数n + 1 个结点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        //要删除倒数第n个结点,先找到倒数第n + 1个结点
        ListNode x = findKthFromEnd(dummy, n + 1);
        //删除倒数第n个结点
        x.next = x.next.next;
        return dummy.next;
    }

    public ListNode findKthFromEnd(ListNode head, int k) {
        ListNode p1 = head;
        //p1走了k步
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }
        ListNode p2 = head;
        ListNode p2Pre = p2;
        //p1,p2同时走n - k 步
        while (p1 != null) {
            p2Pre = p2;
            p2 = p2.next;
            p1 = p1.next;
        }
        //p2指向第n - k + 1个结点,即为倒数第k个结点
        return p2;
    }
}
