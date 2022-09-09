package ch06_0_1背包问题;

/**
 * @Author mapKey
 * @Date 2022-09-09-8:39 AM
 */
class Solution1 {
    int knapsack(int W, int N, int[] wt, int[] val) {
        // base case 已初始化
        // 状态有两个，就是「背包的容量」和「可选择的物品」
        //**选择就是「装进背包」或者「不装进背包」
    /*    `dp[i][w]` 的定义如下：对于前 `i` 个物品，
         当前背包的容量为 `w`，这种情况下可以装的最大价值是 `dp[i][w]`。*/
        int[][] dp = new int[N + 1][W + 1];
        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= W; w++) {
                if (w - wt[i - 1] < 0) {
                    // 这种情况下只能选择不装入背包
                    dp[i][w] = dp[i - 1][w];
                } else {
                    // 装入或者不装入背包，择优
                    dp[i][w] = Math.max(
                            dp[i - 1][w - wt[i - 1]] + val[i - 1],
                            dp[i - 1][w]
                    );
                }
            }
        }
        return dp[N][W];
    }
}


class Solution {
    public int knapsack(int N, int W, int[] wt, int[] val) {
            /*    `dp[i][w]` 的定义如下：对于前 `i` 个物品，
         当前背包的容量为 `w`，这种情况下可以装的最大价值是 `dp[i][w]`。*/
        int[][] dp = new int[N + 1][W + 1];
        //base case
        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= W; w++) {
                if (w - wt[i - 1] < 0) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    dp[i][w] = Math.max(
                            dp[i - 1][w],
                            dp[i - 1][w - wt[i - 1]] + val[i - 1]
                    );
                }
            }
        }
        return dp[N][W];
    }
}
