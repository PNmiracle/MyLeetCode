package ch11动态规划帮我通关辐射4.a_514自由之路;

import java.util.*;

/**
 * @Author mapKey
 * @Date 2022-09-12-2:15 PM
 */
class Solution1 {
    // 字符 -> 索引列表
    HashMap<Character, List<Integer>> charToIndex = new HashMap<>();
    // 备忘录
    int[][] memo;

    /* 主函数 */
    int findRotateSteps(String ring, String key) {
        int m = ring.length();
        int n = key.length();
        // 备忘录全部初始化为 0
        memo = new int[m][n];
        // 记录圆环上字符到索引的映射
        for (int i = 0; i < ring.length(); i++) {
            char c = ring.charAt(i);
            if (!charToIndex.containsKey(c)) {
                charToIndex.put(c, new LinkedList<>());
            }
            charToIndex.get(c).add(i);
        }
        // 圆盘指针最初指向 12 点钟方向，
        // 从第一个字符开始输入 key
        return dp(ring, 0, key, 0);
    }
    //**「状态」就是「当前需要输入的字符」和「当前圆盘指针的位置」**。
    //**「选择」就是「如何拨动指针得到待输入的字符」**。
    // 计算圆盘指针在 ring[i]，输入 key[j..] 的最少操作数
    //
    int dp(String ring, int i, String key, int j) {
        // base case 完成输入
        if (j == key.length()) return 0;
        // 查找备忘录，避免重叠子问题
        if (memo[i][j] != 0) return memo[i][j];

        int m = ring.length();
        // 做选择
        int res = Integer.MAX_VALUE;
        // ring 上可能有多个字符 key[j]
        for (int k : charToIndex.get(key.charAt(j))) {
            // 拨动指针的次数
            int delta = Math.abs(k - i);
            // 选择顺时针还是逆时针
            delta = Math.min(delta, m - delta);
            // 将指针拨到 ring[k]，继续输入 key[j+1..]
            int subProblem = dp(ring, k, key, j + 1);
            // 选择「整体」操作次数最少的
            // 加一是因为按动按钮也是一次操作
            res = Math.min(res, 1 + delta + subProblem);
        }
        // 将结果存入备忘录
        memo[i][j] = res;
        return res;
    }
}


class Solution {
    HashMap<Character, List<Integer>> charToIndex = new HashMap<>();
    int[][] memo;
    public int findRotateSteps(String ring, String key) {
        int m = ring.length();
        int n = key.length();
        memo = new int[m][n];
        for (int i = 0; i < ring.length(); i++) {
            char c = ring.charAt(i);
            if (!charToIndex.containsKey(c)) {
                charToIndex.put(c, new ArrayList<>());
            }
            charToIndex.get(c).add(i);
        }
        return dp(ring, 0, key, 0);
    }
    //**「状态」就是「当前需要输入的字符」和「当前圆盘指针的位置」**。
    //**「选择」就是「如何拨动指针得到待输入的字符」**。
    // 计算圆盘指针在 ring[i]，输入 key[j..] 的最少操作数
    private int dp(String ring, int i, String key, int j) {
        // base case
        if (j == key.length()) {
            return 0;
        }

        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        int m = ring.length();
        int res = Integer.MAX_VALUE;
        char c = key.charAt(j);
        for (Integer k : charToIndex.get(c)) {
            int delta = Math.abs(i - k);
            delta = Math.min(delta, m - delta);
            int subProblem = dp(ring, k, key, j + 1);
            res = Math.min(res, 1 + delta + subProblem);
        }

        memo[i][j] = res;
        return res;
    }
}