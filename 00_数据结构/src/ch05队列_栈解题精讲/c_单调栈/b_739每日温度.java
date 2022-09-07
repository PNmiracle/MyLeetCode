package ch05队列_栈解题精讲.c_单调栈;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @Author mapKey
 * @Date 2022-08-30-2:20 PM
 */
class b1_Solution{
    int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        // 这里放元素索引，而不是元素
        //Stack<Integer> s = new Stack<>();
        LinkedList<Integer> s = new LinkedList<>();
        /* 单调栈模板 */
        for (int i = n - 1; i >= 0; i--) {
            while (!s.isEmpty() && temperatures[s.peek()] <= temperatures[i]) {
                s.pop();
            }
            // 得到索引间距
            res[i] = s.isEmpty() ? 0 : (s.peek() - i);
            // 将索引入栈，而不是元素
            s.push(i);
        }
        return res;
    }
}

class b_mySolution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Stack<Integer[]> s = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!s.isEmpty() && temperatures[i] >= s.peek()[1]) {
                s.pop();
            }

            res[i] = s.isEmpty() ? 0 : s.peek()[0] - i;
            s.push(new Integer[]{i, temperatures[i]});
        }

        return res;
    }
}

class b1_my_Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        // 单调栈: 存放索引
        Stack<Integer> s = new Stack<>();

        for (int i = temperatures.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && temperatures[i] >= temperatures[s.peek()]) {
                s.pop();
            }

            res[i] = s.isEmpty() ? 0 : s.peek() - i;
            s.push(i);
        }
        return res;
    }
}