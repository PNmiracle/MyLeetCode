package ch05队列_栈解题精讲.a_栈的经典习题;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @Author mapKey
 * @Date 2022-08-29-7:36 AM
 *
 */
public class e_155最小栈 {
    // 原始思路
    class MinStack1 {
        // 记录栈中的所有元素
        Stack<Integer> stk = new Stack<>();
        // 阶段性记录栈中的最小元素
        //用一个额外的栈 minStk 来记录栈中每个元素下面（到栈底）的最小元素是多少
        Stack<Integer> minStk = new Stack<>();

        public void push(int val) {
            stk.push(val);
            // 维护 minStk 栈顶为全栈最小元素
            if (minStk.isEmpty() || val <= minStk.peek()) {
                // 新插入的这个元素就是全栈最小的
                minStk.push(val);
            } else {
                // 插入的这个元素比较大
                minStk.push(minStk.peek());
            }
        }

        public void pop() {
            stk.pop();
            minStk.pop();
        }

        public int top() {
            return stk.peek();
        }

        public int getMin() {
            // minStk 栈顶为全栈最小元素
            return minStk.peek();
        }
    }
    // 优化版
    class MinStack_minStk元素半不重复 {
        // 记录栈中的所有元素
        Stack<Integer> stk = new Stack<>();
        // 阶段性记录栈中的最小元素
        Stack<Integer> minStk = new Stack<>();

        public void push(int val) {
            stk.push(val);
            // 维护 minStk 栈顶为全栈最小元素
            if (minStk.isEmpty() || val <= minStk.peek()) {
                // 新插入的这个元素就是全栈最小的
                minStk.push(val);
            }
        }

        public void pop() {
            // 注意 Java 的语言特性，比较 Integer 相等要用 equals 方法
            if (stk.peek().equals(minStk.peek())) {
                // 弹出的元素是全栈最小的
                minStk.pop();
            }
            stk.pop();
        }

        public int top() {
            return stk.peek();
        }

        public int getMin() {
            // minStk 栈顶为全栈最小元素
            return minStk.peek();
        }
    }
}

class MinStack {
    private LinkedList<Integer> stk = new LinkedList<>();
    private LinkedList<Integer> minStk = new LinkedList<>();


    public MinStack() {

    }

    public void push(int val) {
        stk.push(val);
        if (minStk.isEmpty() || minStk.peek() >= val) {
            minStk.push(val);
        }
    }

    public void pop() {
        if (stk.peek().equals(minStk.peek()))
            minStk.pop();
        stk.pop();
    }

    public int top() {
        return stk.peek();
    }

    public int getMin() {
        return minStk.peek();
    }
}
