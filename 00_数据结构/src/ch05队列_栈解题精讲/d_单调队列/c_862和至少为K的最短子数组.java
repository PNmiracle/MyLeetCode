package ch05队列_栈解题精讲.d_单调队列;

import java.util.LinkedList;

/**
 * @Author mapKey
 * @Date 2022-09-03-1:47 PM
 */
class c1_Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        // 看题目的数据范围，前缀和数组中元素可能非常大，所以用 long 类型
        long[] preSum = new long[n + 1];
        preSum[0] = 0;
        // 计算 nums 的前缀和数组
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        // 单调队列结构辅助滑动窗口算法
        c1MonotonicQueue window = new c1MonotonicQueue();
        int right = 0, left = 0;
        int len = Integer.MAX_VALUE;
        // 开始执行滑动窗口算法框架
        while (right < preSum.length) {
            // 扩大窗口，元素入队
            window.offer(preSum[right]);
            right++;
            // 若新进入窗口的元素和窗口中的最小值之差大于等于 k，
            // 说明得到了符合条件的子数组，缩小窗口，使子数组长度尽可能小
            //while ( !window.isEmpty()
            //        && preSum[right] - window.min() >= k) {
            while (right < preSum.length && !window.isEmpty()
                    && preSum[right] - window.min() >= k) {
                // 更新答案
                len = Math.min(len, right - left);
                // 缩小窗口
                window.poll();
                left++;
            }
        }
        return len == java.lang.Integer.MAX_VALUE ? -1 : len;
    }
}

/*
单调队列的通用实现，可以高效维护最大值和最小值
由于考虑泛型和通用性，提交的性能会略差，你可自行精简
*/
class c1MonotonicQueue {
    private LinkedList<Long> q = new LinkedList<>();
    private LinkedList<Long> maxq = new LinkedList<>();
    private LinkedList<Long> minq = new LinkedList<>();

    public void offer(Long elem) {
        q.addLast(elem);

        //while (!maxq.isEmpty() && maxq.getLast() < elem) {
        while (!maxq.isEmpty() && maxq.getLast().compareTo(elem) < 0) {
            maxq.pollLast();
        }
        maxq.addLast(elem);

        //while (!minq.isEmpty() && minq.getLast() < elem) {
        while (!minq.isEmpty() && minq.getLast().compareTo(elem) > 0) {
            minq.pollLast();
        }
        minq.addLast(elem);
    }

    public Long max() {
        return maxq.getFirst();
    }

    public Long min() {
        return minq.getFirst();
    }

    public Long poll() {
        Long deleted = q.poll();

        if (deleted.equals(maxq.getFirst())) {
            maxq.poll();
        }
        if (deleted.equals(minq.getFirst())) {
            minq.poll();
        }

        return deleted;
    }

    public boolean isEmpty() {
        return q.isEmpty();
    }

    public int size() {
        return q.size();
    }
}



class 错误_Solution {
    public int shortestSubarray(int[] nums, int k) {
        long[] preSum = new long[nums.length + 1];
        preSum[0] = 0;
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        int left = 0, right = 0;
        int len = Integer.MAX_VALUE;
        MonotonicQueue window = new MonotonicQueue();
        while (right < preSum.length) {
            window.offer(preSum[right]);
            right++;

            while (right < preSum.length && !window.isEmpty() && preSum[right] - window.min() >= k) {

                len = Math.min(len, right - left);

                window.poll();
                left++;
            }
        }
        return len == Integer.MAX_VALUE ? -1 : len;
    }

    private class MonotonicQueue {
        private LinkedList<Long> q = new LinkedList<>();
        private LinkedList<Long> maxq = new LinkedList<>();
        private LinkedList<Long> minq = new LinkedList<>();

        public void offer(Long elem) {
            q.addLast(elem);

            while (!maxq.isEmpty() && maxq.getFirst().compareTo(elem) < 0) {
                maxq.removeLast();
            }
            maxq.addLast(elem);

            while (!minq.isEmpty() && minq.getFirst().compareTo(elem) > 0) {
                minq.removeLast();
            }
            minq.addLast(elem);
        }

        public Long poll() {
            Long deleted = q.poll();
            //这个关键字可以判断布尔值的结果是否和预期的一样，
            // 如果一样就正常执行，否则会抛出AssertionError
            //assert deleted != null;

            if (maxq.getFirst().equals(deleted)) {
                maxq.poll();
            }
            if (minq.getFirst().equals(deleted)) {
                minq.poll();
            }

            return deleted;
        }

        public Long min() {
            return minq.getFirst();
        }

        public long max() {
            return maxq.getFirst();
        }

        public boolean isEmpty() {
            return q.isEmpty();
        }

        public int size() {
            return q.size();
        }
    }
}