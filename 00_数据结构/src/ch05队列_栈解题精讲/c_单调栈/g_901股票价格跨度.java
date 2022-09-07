package ch05队列_栈解题精讲.c_单调栈;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Author mapKey
 * @Date 2022-09-01-7:48 AM
 */
class StockSpanner {
    // int[] 记录 {价格，小于等于该价格的最大连续天数} 二元组
    //Stack<int[]> stk = new Stack<>();
    private LinkedList<int[]> stk = new LinkedList<>();
    public int next(int price) {
        // 算上当天
        int count = 1;
        // 单调栈模板
        while (!stk.isEmpty() && price >= stk.peek()[0]) {
            // 挤掉价格低于 price 的记录
            int[] prev = stk.pop();
            // 计算小于等于 price 的天数
            count += prev[1];
        }
        stk.push(new int[]{price, count});

        return count;
    }
}
class g_错误_StockSpanner {

    private ArrayList<Integer> list = new ArrayList<>();
    private LinkedList<Integer> stk = new LinkedList<>();


    public int next(int price) {
        list.add(price);
        return diffPrevGreaterElem();
    }
    // 返回上一个更大元素的索引 与 当前位置的索引之差
    public int diffPrevGreaterElem() {
        for (int i = 0; i < list.size(); i++) {
            //
            while (!stk.isEmpty() && list.get(stk.peek()) <= list.get(i)) {
                stk.pop();
            }

            stk.push(i);
        }
        int res = stk.isEmpty() ? 1 : list.size() - 1 - stk.peek();
        return res;
    }
}