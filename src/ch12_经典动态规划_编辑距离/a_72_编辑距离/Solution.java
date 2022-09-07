package ch12_经典动态规划_编辑距离.a_72_编辑距离;

import java.util.Arrays;


class a_Solution {
    public int minDistance(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        // 定义：s1[0..i - 1] 和 s2[0..j - 1] 的最小编辑距离是 dp[i][j]
        int[][] dp = new int[m + 1][n + 1];
        // base case
        for (int i = 1; i <= m; i++)
            dp[i][0] = i;
        for (int j = 1; j <= n; j++)
            dp[0][j] = j;
        // 自底向上求解
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = min(
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1,
                            dp[i - 1][j - 1] + 1
                    );
        // 储存着整个 s1 和 s2 的最小编辑距离
        return dp[m][n];
    }

    int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
// 详细解析参见：
// https://labuladong.github.io/article/?qno=72

class b_Solution {
    // 备忘录
    int[][] memo;

    public int minDistance(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        // 备忘录初始化为特殊值，代表还未计算
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(s1, m - 1, s2, n - 1);
    }


    // 定义：返回 s1[0..i] 和 s2[0..j] 的最小编辑距离
    int dp(String s1, int i, String s2, int j) {
        //操作数
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;
        // 查备忘录，避免重叠子问题
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // 状态转移，结果存入备忘录
        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = dp(s1, i - 1, s2, j - 1);
        } else {
            memo[i][j] = min(
                    dp(s1, i, s2, j - 1) + 1,
                    dp(s1, i - 1, s2, j) + 1,
                    dp(s1, i - 1, s2, j - 1) + 1
            );
        }
        return memo[i][j];
    }

    int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

}


class test1Solution {
    int[][] memo;

    public int minDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dp(s1, m - 1, s2, n - 1);

    }

    private int dp(String s1, int i, String s2, int j) {
        //if (i < 0) return j + 1;
        //if (j < 0) return i + 1;
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = dp(s1, i - 1, s2, j - 1);
        } else {
            memo[i][j] = min(dp(s1, i - 1, s2, j) + 1,
                    dp(s1, i, s2, j - 1) + 1,
                    dp(s1, i - 1, s2, j - 1) + 1
            );
        }

        return memo[i][j];

    }

    private int min(int i, int j, int k) {
        return Math.min(i, Math.min(j, k));
    }
}


class Solution {
    public int minDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        //dp[i][j]  s1[0...i-1], s2[0...j-1]
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = myMin(
                            dp[i - 1][j - 1] + 1,
                            dp[i][j - 1] + 1,
                            dp[i - 1][j] + 1
                    );
                }
            }
        }
        return dp[m][n];
    }

    private int myMin(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}