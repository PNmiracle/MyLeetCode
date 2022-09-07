package ch01_SingleLinkedList.a_MergeTwoLists;

/**
 * @Author mapKey
 * @Date 7/17/2022 9:29 PM
 * @Since version-1.0
 * @Description
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。
 * 新链表是通过拼接给定的两个链表的所有节点组成的。
 * https://leetcode.cn/problems/merge-two-sorted-lists/
 */
public class a_MergeTwoLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //虚拟头结点,(不移动)
        ListNode dummy = new ListNode(-1);
        //p为新链的指针
        ListNode p = dummy;
        //p1,p2指针来 遍历两个链表
        ListNode p1 = list1, p2 = list2;
        //
        while (p1 != null && p2 != null) {
            //比较p1,p2,将较小的指针接在p上
            if (p1.val > p2.val) {
                //更新数据
                p.next = p2;
                //迭代
                p2 = p2.next;
            } else {
                p.next = p1;
                p1 = p1.next;
            }
            //相当于i++
            p = p.next;
        }
        //把剩下的接在末尾
        if (p1 != null) {
            p.next = p1;
        }

        if (p2 != null) {
            p.next = p2;
        }

        return dummy.next;
    }
}
