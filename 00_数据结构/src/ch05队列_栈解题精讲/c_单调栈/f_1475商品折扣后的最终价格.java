package ch05队列_栈解题精讲.c_单调栈;

import java.util.LinkedList;

/**
 * @Author mapKey
 * @Date 2022-08-31-7:51 AM
 */
class f1_Solution {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] res = new int[n];
        LinkedList<Integer> stk = new LinkedList<>();
        for (int i = prices.length - 1; i >= 0; i--) {
            //目标 stk.peek <= prices[i]
            while (!stk.isEmpty() && stk.peek() > prices[i]) {
                stk.pop();
            }
            res[i] = stk.isEmpty() ? prices[i] : prices[i] - stk.peek();
            stk.push(prices[i]);
        }

        return res;
    }
}
