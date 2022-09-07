package ch05队列_栈解题精讲.c_单调栈;

import java.util.LinkedList;

/**
 * @Author mapKey
 * @Date 2022-08-30-2:55 PM
 */
class c1_Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        LinkedList<Integer> s = new LinkedList<>();
        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!s.isEmpty() && nums[i % n] >= s.peek()) {
                s.pop();
            }
            res[i % n] = s.isEmpty() ? -1 : s.peek();
            s.push(nums[i % n]);
        }
        return res;
    }
}