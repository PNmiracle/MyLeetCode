package ch05队列_栈解题精讲.a_栈的经典习题;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @Author mapKey
 * @Date 2022-08-24
 */
class c_1_Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stk = new Stack<>();
        for (String token : tokens) {
            if ("+-*/".contains(token)) {
                // 是个运算符，从栈顶拿出两个数字进行运算，运算结果入栈
                int a = stk.pop(), b = stk.pop();
                switch (token) {
                    case "+":
                        stk.push(a + b);
                        break;
                    case "*":
                        stk.push(a * b);
                        break;
                    // 对于减法和除法，顺序别搞反了，第二个数是被除（减）数
                    case "-":
                        stk.push(b - a);
                        break;
                    case "/":
                        stk.push(b / a);
                        break;
                }
            } else {
                // 是个数字，直接入栈即可
                stk.push(Integer.parseInt(token));
            }
        }
        // 最后栈中剩下一个数字，即是计算结果
        return stk.pop();
    }
}

class c_1_My_Solution {
    public int evalRPN(String[] tokens) {
        LinkedList<Integer> stk = new LinkedList<>();

        for (String token : tokens) {
            if ("+-*/".contains(token)) {
                int a = stk.pop();
                int b = stk.pop();

                switch (token) {
                    case "+" :
                        stk.push(b + a);
                        break;
                    case "*" :
                        stk.push(b * a);
                        break;
                    case "-":
                        stk.push(b - a);
                        break;
                    case "/":
                        stk.push(b / a);
                        break;
                }
            } else {
                //stk.push(token);
                stk.push(Integer.parseInt(token));
            }
        }

        return stk.pop();
    }
}
