package ch05队列_栈解题精讲.c_单调栈;

import java.util.LinkedList;

/**
 * @Author mapKey
 * @Date 2022-08-31-7:34 AM
 */
class e1_Solution {
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] res = new int[n];
        // int[] 记录 {身高，小于等于该身高的人数} 二元组
        //Stack<Integer> stk = new Stack<>();
        LinkedList<Integer> stk = new LinkedList<>();
        for (int i = n - 1; i >= 0; i--) {
            // 记录右侧比自己矮的人
            int count = 0;
            // 单调栈模板，计算下一个更大或相等元素（身高）,
            // 目标是保证stk.peek() >= heights[i]
            while (!stk.isEmpty() && heights[i] > stk.peek()) {
                stk.pop();
                count++;
            }
            // 不仅可以看到比自己矮的人，如果后面存在更高的的人，也可以看到这个高人
            res[i] = stk.isEmpty() ? count : count + 1;
            stk.push(heights[i]);
        }
        return res;
    }
}