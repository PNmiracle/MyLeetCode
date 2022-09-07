package ch05队列_栈解题精讲.a_栈的经典习题;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @Author mapKey
 * @Date 2022-08-24
 */
class b_1_Solution {
    public boolean isValid(String str) {
        Stack<Character> left = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == '(' || c == '{' || c == '[')
                left.push(c);
            else // 字符 c 是右括号
                if (!left.isEmpty() && leftOf(c) == left.peek())
                    left.pop();
                else
                    // 和最近的左括号不匹配
                    return false;
        }
        // 是否所有的左括号都被匹配了
        return left.isEmpty();
    }

    char leftOf(char c) {
        if (c == '}') return '{';
        if (c == ')') return '(';
        return '[';
    }
}

class b_1_My_Solution {
    public boolean isValid(String str) {
        // left栈装左括号
        LinkedList<Character> left = new LinkedList<>();
        for (Character c : str.toCharArray()) {
            if (c == '(' || c == '[' || c == '{')
                left.push(c);
            else // 字符 c 是右括号
                if (!left.isEmpty() && leftOf(c) == left.peek())
                    left.pop();
                else
            // 和最近的左括号不匹配
                    return false;
        }
        // 是否所有的左括号都被匹配了
        return left.isEmpty();
    }

    char leftOf(char c) {
        if (c == '}') return '{';
        if (c == ')') return '(';
        return '[';
    }
}


class b_my_Solution {
    public boolean isValid(String s) {
        LinkedList<Character> stk = new LinkedList<>();
        boolean isValid = true;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (isLeft(c)) {
                stk.push(c);
            } else {
                if (stk.isEmpty()) {
                    return false;
                }
                Character top = stk.pop();
                if (isMatch(top, c)) {

                } else {
                    //isValid = false;
                    //break;
                    return false;
                }
            }
        }

        return stk.isEmpty();
    }

    private boolean isMatch(Character left, Character right) {
        if (left == '(') {
            return right == ')';
        }
        if (left == '[') {
            return right == ']';
        }

        if (left == '{') {
            return right == '}';
        }

        return false;
    }

    private boolean isLeft(char c) {
        if (c == '(' || c == '[' || c == '{') {
            return true;
        }
        return false;
    }
}
