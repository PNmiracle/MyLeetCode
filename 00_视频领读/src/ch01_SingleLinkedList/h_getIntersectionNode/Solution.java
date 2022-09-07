package ch01_SingleLinkedList.h_getIntersectionNode;

/**
 * @Author mapKey
 * @Date 7/19/2022 10:56 AM
 * @Since version-1.0
 * @Description
 * 160. 相交链表
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。
 * 如果两个链表不存在相交节点，返回 null
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
    /*A链表B链表拼接在一起,配平相遇前长度不一的情况*/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //p1指向A链表的头结点,p2指向B链表的头结点
        ListNode p1 = headA, p2 = headB;
        //结束条件:p1 p2相遇
        while (p1 != p2) {
            //p1走一步,如果走到A链表末尾,转到B链表头
            if (p1 == null) {
                p1 = headB;
            } else {
                p1 = p1.next;
            }

            if (p2 == null) {
                p2 = headA;
            } else{
                p2 = p2.next;
            }
        }
        //return p1 == p2 ? p1 : null;
        //考虑到没有相交部分的情况,p1此时走到拼接后的A + B 最后的null
        return p1;
    }

    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        int lenA = 0, lenB = 0;
        // 计算两条链表的长度

        for (ListNode p1 = headA; p1 != null; p1 = p1.next) {
            lenA++;
        }
        for (ListNode p2 = headB; p2 != null; p2 = p2.next) {
            lenB++;
        }
        // 让 p1 和 p2 到达尾部的距离相同
        ListNode p1 = headA, p2 = headB;
        if (lenA > lenB) {
            for (int i = 0; i < lenA - lenB; i++) {
                p1 = p1.next;
            }
        } else {
            for (int i = 0; i < lenB - lenA; i++) {
                p2 = p2.next;
            }
        }
        // 看两个指针是否会相同，p1 == p2 时有两种情况：
        // 1、要么是两条链表不相交，他俩同时走到尾部空指针
        // 2、要么是两条链表相交，他俩走到两条链表的相交点
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

}