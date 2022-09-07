package ch05队列_栈解题精讲.d_单调队列;

import java.util.LinkedList;

/**
 * @Author mapKey
 * @Date 2022-09-03-8:11 AM
 */
class b1_Solution {
    public int longestSubarray(int[] nums, int limit) {
        int left = 0, right = 0;
        int windowSize = 0, res = 0;
        b1_MonotonicQueue window = new b1_MonotonicQueue();
        // 滑动窗口模板
        while (right < nums.length) {
            // 扩大窗口，更新窗口最值
            window.push(nums[right]);
            right++;
            windowSize++;
            while (window.max() - window.min() > limit) {
                // 缩小窗口，更新窗口最值
                window.pop();
                left++;
                windowSize--;
            }
            // 在窗口收缩判断完之后才更新答案
            res = Math.max(res, windowSize);
        }
        return res;
    }
}

/*
单调队列的通用实现，可以高效维护最大值和最小值
由于考虑泛型和通用性，提交的性能会略差，你可自行精简
*/
class b1_MonotonicQueue {
    // 常规队列，存储所有元素
    LinkedList<Integer> q = new LinkedList<>();
    // 元素降序排列的单调队列，头部是最大值
    LinkedList<Integer> maxq = new LinkedList<>();
    // 元素升序排列的单调队列，头部是最小值
    LinkedList<Integer> minq = new LinkedList<>();

    public void push(Integer elem) {
        // 维护常规队列，直接在队尾插入元素
        q.addLast(elem);

        // 维护 maxq，将小于 elem 的元素全部删除
        while (!maxq.isEmpty() && maxq.getLast().compareTo(elem) < 0) {
            maxq.pollLast();
        }
        maxq.addLast(elem);

        // 维护 minq，将大于 elem 的元素全部删除
        while (!minq.isEmpty() && minq.getLast().compareTo(elem) > 0) {
            minq.pollLast();
        }
        minq.addLast(elem);
    }

    public Integer max() {
        // maxq 的头部是最大元素
        return maxq.getFirst();
    }

    public Integer min() {
        // minq 的头部是最大元素
        return minq.getFirst();
    }

    public Integer pop() {
        // 从标准队列头部弹出需要删除的元素
        Integer deleteVal = q.pollFirst();
        assert deleteVal != null;

        // 由于 push 的时候会删除元素，deleteVal 可能已经被删掉了
        if (deleteVal.equals(maxq.getFirst())) {
            maxq.pollFirst();
        }
        if (deleteVal.equals(minq.getFirst())) {
            minq.pollFirst();
        }
        return deleteVal;
    }

    public int size() {
        // 标准队列的大小即是当前队列的大小
        return q.size();
    }

    public boolean isEmpty() {
        return q.isEmpty();
    }
}


class b1_Solution1 {
    public int longestSubarray(int[] nums, int limit) {
        int left = 0, right = 0;
        int maxLen = 0;
        int res = 0;
        MonotonicQueue window = new MonotonicQueue();

        while (right < nums.length) {
            window.offer(nums[right]);
            right++;
            maxLen++;

            while (window.max() - window.min() > limit) {
                window.poll();
                left++;
                maxLen--;
            }
            res = Math.max(res, maxLen);
        }
        return res;
    }

    private class MonotonicQueue {
        LinkedList<Integer> q = new LinkedList<>();
        LinkedList<Integer> maxq = new LinkedList<>();
        LinkedList<Integer> minq = new LinkedList<>();

        public void offer(Integer x) {
            q.addLast(x);

            while (!maxq.isEmpty() && maxq.getLast() < x) {
                maxq.removeLast();
            }
            maxq.addLast(x);

            while (!minq.isEmpty() && minq.getLast() > x) {
                minq.removeLast();
            }
            minq.addLast(x);
        }

        public Integer max() {
            return maxq.getFirst();
        }

        public Integer min() {
            return minq.getFirst();
        }

        public Integer poll() {
            Integer deleted = q.removeFirst();
            if (maxq.getFirst() == deleted) {
                maxq.removeFirst();
            }

            if (minq.getFirst() == deleted) {
                minq.removeFirst();
            }

            return deleted;
        }


        public int size() {
            return q.size();
        }

        public boolean isEmpty() {
            return q.isEmpty();
        }
    }
}