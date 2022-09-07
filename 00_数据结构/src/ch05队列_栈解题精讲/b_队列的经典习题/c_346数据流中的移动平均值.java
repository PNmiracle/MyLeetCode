package ch05队列_栈解题精讲.b_队列的经典习题;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author mapKey
 * @Date 2022-08-29-8:45 AM
 */
class MovingAverage {
    private Queue<Integer> q = new LinkedList<>();
    private int maxSize, queueSum;

    public MovingAverage(int size) {
        this.maxSize = size;
    }

    public double next(int val) {
        if (q.size() == maxSize) {
            Integer deletedVal = q.poll();
            queueSum -= deletedVal;
        }
        q.offer(val);
        queueSum += val;

        return (double) queueSum / q.size();

    }
}
