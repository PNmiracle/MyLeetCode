package ch05队列_栈解题精讲.d_单调队列;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author mapKey
 * @Date 2022-09-04-7:28 AM
 */
// 第四步，利用单调队列结构消除内层循环（通过）
class e1_Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        e1_MonotonicQueue window = new e1_MonotonicQueue();
        // 定义：到达 nums[p] 的最大分数为 dp[p]
        int[] dp = new int[n];
        // dp 数组初始化为最小值
        Arrays.fill(dp, Integer.MIN_VALUE);
        // base case
        dp[0] = nums[0];
        window.push(dp[0]);
        // 状态转移
        for (int p = 1; p < n; p++) {
            dp[p] = window.max() + nums[p];
            // 维护窗口装着 dp[p-1..p-k]
            if (window.size() == k) {
                window.pop();
            }
            window.push(dp[p]);
        }
        return dp[n - 1];
    }
}

/*
单调队列的通用实现，可以高效维护最大值和最小值
由于考虑泛型和通用性，提交的性能会略差，你可自行精简
*/
class e1_MonotonicQueue {
    private LinkedList<Integer> q = new LinkedList<>();
    private LinkedList<Integer> maxq = new LinkedList<>();

    public void push(Integer elem) {
        q.addLast(elem);

        while (!maxq.isEmpty() && maxq.getLast() < elem) {
            maxq.pollLast();
        }
        maxq.addLast(elem);
    }

    public Integer pop() {
        Integer deleted = q.poll();
        assert deleted != null;
        if (deleted.equals(maxq.getFirst())) {
            maxq.pop();
        }

        return deleted;
    }

    public Integer max() {
        return maxq.getFirst();
    }

    public boolean isEmpty() {
        return q.isEmpty();
    }

    public int size() {
        return q.size();
    }
}


class e1_my_Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        e1_MonotonicQueue window = new e1_MonotonicQueue();
        //dp[p]存放到nums[p]的最大得分
        int[] dp = new int[n];
        //Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = nums[0];
        window.push(dp[0]);
        for (int p = 1; p < n; p++) {
            dp[p] = window.max() + nums[p];

            if (window.size() == k) {
                window.pop();
            }
            window.push(dp[p]);
        }

        return dp[n - 1];
    }
}