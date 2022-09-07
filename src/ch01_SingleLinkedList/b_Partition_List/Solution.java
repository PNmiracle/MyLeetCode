package ch01_SingleLinkedList.b_Partition_List;

/**
 * @Author mapKey
 * @Date 7/17/2022 9:37 PM
 * @Since version-1.0
 * @Description 86. 分隔链表
 * https://leetcode.cn/problems/partition-list/
 * 给你一个链表的头节点 head 和一个特定值 x ，
 * 请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * 你应当 保留 两个分区中每个节点的初始相对位置。

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
/*区分成为两个不同的部分,然后再拼接成一个链表*/
public class Solution {
    public ListNode partition(ListNode head, int x) {
        //存放小于x的链表的虚拟头结点
        ListNode dummy1 = new ListNode(-1);
        ListNode dummy2 = new ListNode(-1);
        ListNode p = head, p1 = dummy1, p2 = dummy2;

        while (p != null) {
            if (p.val < x) {
                p1.next = p;
                p1 = p1.next;
            } else {
                p2.next = p;
                p2 = p2.next;
            }
            //断开原链表中每个节点的next指针,
            //否则会造成新链表指针混乱 p++
            //新建一个temp保存p.next的地址信息
            ListNode temp = p.next;
            //断掉的操作核心
            p.next = null;
            p = temp;
        }
        //连接两个链表
        p1.next = dummy2.next;

        return dummy1.next;
    }
}