package ch01_SingleLinkedList.c_Merge_K_Sorted_Lists;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author mapKey
 * @Date 7/18/2022 4:21 PM
 * @Since version-1.0
 * @Description 23. 合并K个升序链表
 * https://leetcode.cn/problems/merge-k-sorted-lists/
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

/*
* 给你一个链表数组，每个链表都已经按升序排列。

请你将所有链表合并到一个升序链表中，返回合并后的链表。
* 优先队列 pq 中的元素个数最多是 k，
* 所以一次 poll 或者 add 方法的时间复杂度是 O(logk)；
* 所有的链表节点都会被加入和弹出 pq，所以算法整体的时间复杂度是 O(Nlogk)，其中 k 是链表的条数，N 是这些链表的节点总数。*/
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        /*题目条件
           k == lists.length
           0 <= k <= 10^4*/
        if (lists.length == 0) {
            return null;
        }
        //结果链表的虚拟头结点,和用作移动的p指针
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        //PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length,
        //        (a, b) -> (a.val - b.val));lambda表达式
        //优先级队列,最小堆 构造器中第一个参数initialCapacity < 1要抛异常
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length,
                new Comparator<ListNode>() {
                    @Override
                    public int compare(ListNode o1, ListNode o2) {
                        return o1.val - o2.val;
                    }
                });
        //初始化,将k个链表的头结点加入最小堆
        for (ListNode head : lists) {
            //
            if (head != null) {
                //保证添加的元素不为null,否则抛出异常:throw new NullPointerException();
                pq.add(head);
            }
        }

        while (!pq.isEmpty()) {
            //获取优先级队列中的最小元素(最小堆的首元素)
            ListNode node = pq.poll();
            //将最小元素添加到结果链表中
            p.next = node;
            //node.next移动到末尾为null
            if (node.next != null) {
                pq.add(node.next);
            }
            //p指针不断前进
            p = p.next;
        }
        //舍弃dummy后就位结果链表
        return dummy.next;
    }
}
