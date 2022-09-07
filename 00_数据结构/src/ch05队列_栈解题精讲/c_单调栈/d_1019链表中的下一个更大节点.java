package ch05队列_栈解题精讲.c_单调栈;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Author mapKey
 * @Date 2022-08-31-7:15 AM
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

class d1_Solution {
    public int[] nextLargerNodes(ListNode head) {
        ArrayList<Integer> nums = new ArrayList<>();
        for (ListNode p = head; p != null; p = p.next) {
            nums.add(p.val);
        }
        int[] ans = new int[nums.size()];
        LinkedList<Integer> stk = new LinkedList<>();
        for (int i = nums.size() - 1; i >= 0; i--) {
            while (!stk.isEmpty() && stk.peek() <= nums.get(i)) {
                stk.pop();
            }
            ans[i] = stk.isEmpty() ? 0 : stk.peek();
            stk.push(nums.get(i));
        }

        return ans;
    }
}