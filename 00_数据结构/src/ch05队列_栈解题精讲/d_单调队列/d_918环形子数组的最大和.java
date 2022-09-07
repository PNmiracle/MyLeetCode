package ch05队列_栈解题精讲.d_单调队列;

import java.util.LinkedList;

/**
 * @Author mapKey
 * @Date 2022-09-03-6:47 PM
 */
class d1_Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        // 模拟环状的 nums 数组
        int[] preSum = new int[2 * n + 1];
        preSum[0] = 0;
        // 计算环状 nums 的前缀和
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + nums[(i - 1) % n];
        }
        // 记录答案
        int maxSum = Integer.MIN_VALUE;
        // 维护一个滑动窗口，以便根据窗口中的最小值计算最大子数组和
        d1_MonotonicQueue window = new d1_MonotonicQueue();
        window.push(0);
        for (int i = 1; i < preSum.length; i++) {
            maxSum = Math.max(maxSum, preSum[i] - window.min());
            // 维护窗口的大小为 nums 数组的大小
            if (window.size() == n) {
                window.pop();
            }
            window.push(preSum[i]);
        }

        return maxSum;
    }
}

class d1_my_Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[2 * n + 1];
        preSum[0] = 0;
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + nums[(i - 1) % n];
        }
        int maxSum = Integer.MIN_VALUE;
        d1_MonotonicQueue window = new d1_MonotonicQueue();
        window.push(0);
        for (int i = 1; i < preSum.length; i++) {

        }

        return maxSum;
    }
}

/*
单调队列的通用实现，可以高效维护最大值和最小值
由于考虑泛型和通用性，提交的性能会略差，你可自行精简
*/
class d1_MonotonicQueue {
    private LinkedList<Integer> q = new LinkedList<>();
    private LinkedList<Integer> minq = new LinkedList<>();

    public void push(Integer elem) {
        q.addLast(elem);

        while (!minq.isEmpty() && minq.getLast() > elem) {
            minq.pollLast();
        }
        minq.addLast(elem);
    }

    public Integer pop() {
        Integer deleted = q.poll();
        assert deleted != null;

        if (deleted.equals(minq.getFirst())) {
            minq.pop();
        }

        return deleted;
    }

    public Integer min() {
        return minq.getFirst();
    }

    public int size() {
        return q.size();
    }

    public boolean isEmpty() {
        return q.isEmpty();
    }

}