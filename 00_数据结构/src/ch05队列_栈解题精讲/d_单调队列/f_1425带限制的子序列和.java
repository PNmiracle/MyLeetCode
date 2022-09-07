package ch05队列_栈解题精讲.d_单调队列;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author mapKey
 * @Date 2022-09-04-8:09 AM
 */
// 经过单调队列优化的动态规划解法
class f1_Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        // 定义：dp[i] 表示以 nums[i] 结尾的子序列的最大和
        int[] dp = new int[n];
        //Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = nums[0];
        // 单调队列辅助计算 dp[i-k..i-1] 的最大值
        MonotonicQueue window = new MonotonicQueue();
        window.push(dp[0]);

        for (int i = 1; i < n; i++) {
            // 状态转移方程
            dp[i] = Math.max(nums[i], window.max() + nums[i]);
            // 维护滑动窗口的大小为 k
            if (window.size() == k) {
                window.pop();
            }
            window.push(dp[i]);
        }
        // dp 数组中的最大值就是结果
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }


}

/*
    单调队列的通用实现，可以高效维护最大值和最小值
    由于考虑泛型和通用性，提交的性能会略差，你可自行精简
    */
class MonotonicQueue {
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


class Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];

        dp[0] = nums[0];
        MonotonicQueue window = new MonotonicQueue();
        window.push(dp[0]);
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], window.max() + nums[i]);
            if (window.size() == k) {
                window.pop();
            }
            window.push(dp[i]);
        }

        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

}