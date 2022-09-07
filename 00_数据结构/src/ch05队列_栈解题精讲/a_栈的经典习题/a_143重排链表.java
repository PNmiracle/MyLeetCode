package ch05队列_栈解题精讲.a_栈的经典习题;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @Author mapKey
 * @Date 2022-08-20
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

class a_Solution {
    public void reorderList(ListNode head) {
        Stack<ListNode> stk = new Stack<>();
        // 先把所有节点装进栈里，得到倒序结果
        ListNode p = head;
        while (p != null) {
            stk.push(p);
            p = p.next;
        }


        p = head;
        while (p != null) {

            // 链表尾部的节点
            ListNode lastNode = stk.pop();
            ListNode next = p.next;
            if (lastNode == next || lastNode.next == next) {
                // 结束条件，链表节点数为奇数或偶数时均适用
                lastNode.next = null;
                break;
            }
            p.next = lastNode;
            lastNode.next = next;
            p = next;
        }
    }
}

class Solution1 {
    public void reorderList(ListNode head) {
        //Stack<ListNode> stk = new Stack<>();
        LinkedList<ListNode> stk = new LinkedList<>();
        // 先把所有节点装进栈里，得到倒序结果
        ListNode p = head;

        while (p != null) {
            stk.push(p);
            p = p.next;
        }

        p = head;
        while (p != null){
            // 链表尾部的节点
            ListNode lastNode = stk.pop();
            ListNode next = p.next;
            // 结束条件，链表节点数为奇数或偶数时均适用
            if (lastNode == next || lastNode.next == next){
                lastNode.next = null;
                break;
            }
            //p:1, p.next:2, lastNode:4
            // 1 -> 2 ... -> 4
            // 1 -> 4 -> 2 ->...
            p.next = lastNode;
            lastNode.next = next;
            p = next;
        }
    }
}