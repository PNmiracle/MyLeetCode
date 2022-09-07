package ch05队列_栈解题精讲.d_单调队列;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author mapKey
 * @Date 2022-09-04-2:00 PM
 */
class g1_FirstUnique {
    // 记录每个元素的出现次数
    HashMap<Integer, Integer> count = new HashMap<>();
    // 从队尾添加元素，队头取出元素
    Queue<Integer> q = new LinkedList<>();

    public g1_FirstUnique(int[] nums) {
        // 初始化队列和哈希表
        for (int elem : nums) {
            add(elem);
        }
    }

    public int showFirstUnique() {
        while (!q.isEmpty()) {
            int elem = q.peek();
            if (count.get(elem) > 1) {
                // 队列中的非唯一元素都弹出
                q.poll();
            } else {
                // 直到找到第一个唯一元素
                return elem;
            }
        }
        // 如果队列弹空了还找不到，那就不存在唯一元素
        return -1;
    }

    public void add(int value) {
        // 加入队列
        q.offer(value);
        // 哈希表中记录出现次数
        int valCount = count.getOrDefault(value, 0);
        count.put(value, valCount + 1);
    }
}


class FirstUnique {

    private HashMap<Integer, Integer> count = new HashMap<>();
    private Queue<Integer> q = new LinkedList<>();

    public FirstUnique(int[] nums) {
        for (int elem : nums) {
            add(elem);
        }
    }

    public int showFirstUnique() {
        while (!q.isEmpty()) {
            Integer elem = q.peek();
            if (count.get(elem) > 1) {
                q.poll();
            } else {
                return elem;
            }
        }
        return -1;
    }

    public void add(int value) {
        q.offer(value);
        Integer cnt = count.getOrDefault(value, 0);
        count.put(value, cnt + 1);
    }
}
