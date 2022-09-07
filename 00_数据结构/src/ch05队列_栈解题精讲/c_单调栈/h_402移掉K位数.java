package ch05队列_栈解题精讲.c_单调栈;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @Author mapKey
 * @Date 2022-09-02-8:11 AM
 */
class h1_Solution {
    public String removeKdigits(String num, int k) {
        Stack<Character> stk = new Stack<>();
        for (char c : num.toCharArray()) {
            // 单调栈代码模板, 上一个更小或相等元素
            while (!stk.isEmpty() && c < stk.peek() && k > 0) {
                stk.pop();
                k--;
            }
            // 防止 0 作为数字的开头
            if (stk.isEmpty() && c == '0') {
                continue;
            }
            stk.push(c);
        }

        // 此时栈中元素单调递增，若 k 还没用完的话删掉栈顶元素
        while (k > 0 && !stk.isEmpty()) {
            stk.pop();
            k--;
        }
        // 若最后没剩下数字，就是 0
        if (stk.isEmpty()) {
            return "0";
        }
        // 将栈中字符转化成字符串
        StringBuilder sb = new StringBuilder();
        while (!stk.isEmpty()) {
            sb.append(stk.pop());
        }
        // 出栈顺序和字符串顺序是反的
        return sb.reverse().toString();
    }
}


class H1_MYSolution {
    public String removeKdigits(String num, int k) {
        LinkedList<Character> stk = new LinkedList<>();
        for (char c : num.toCharArray()) {
            while (!stk.isEmpty() && stk.peek() > c && k > 0) {
                stk.pop();
                k--;
            }
            if (stk.isEmpty() && c == '0') {
                continue;
            }
            stk.push(c);
        }

        while (k > 0 && !stk.isEmpty()) {
            stk.pop();
            k--;
        }

        if (stk.isEmpty()) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        while (!stk.isEmpty()) {
            sb.append(stk.pop());
        }

        return sb.reverse().toString();
    }
}