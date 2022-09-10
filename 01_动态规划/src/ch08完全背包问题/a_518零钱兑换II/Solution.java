package ch08完全背包问题.a_518零钱兑换II;

/**
 * @Author mapKey
 * @Date 2022-09-10-7:53 AM
 */
class Solution1 {
    int change(int amount, int[] coins) {
        int n = coins.length;
        // 若只使用 `coins` 中的前 `i` 个（`i` 从 1 开始计数）硬币的面值，若想凑出金额 `j`，有 `dp[i][j]` 种凑法。
        int[][] dp = new int[n + 1][amount + 1];
        // base case
        for (int i = 0; i <= n; i++)
            dp[i][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++)
                if (j - coins[i - 1] >= 0)
                    dp[i][j] = dp[i - 1][j]
                            + dp[i][j - coins[i - 1]];
                else
                    dp[i][j] = dp[i - 1][j];
        }
        return dp[n][amount];
    }
}


class Solution1_空间压缩 {
    int change(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1; // base case
        for (int i = 0; i < n; i++)
            for (int j = 1; j <= amount; j++)
                if (j - coins[i] >= 0)
                    dp[j] = dp[j] + dp[j - coins[i]];

        return dp[amount];
    }
}


class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        // dp[i][j]只使用前i个面值, 凑出金额j的组合数
        //int[][] dp = new int[n + 1][amount + 1];
        int[] dp = new int[amount + 1];

        //for (int i = 0; i <= n; i++) {
        //    dp[i][0] = 1;
        //}
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i - 1] >= 0) {
                    dp[j] = dp[j] + dp[j - coins[i - 1]];
                }
            }
        }
        return dp[amount];
    }
}
