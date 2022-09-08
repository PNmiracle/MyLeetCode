package ch04最小路径和系列.c_174地下城游戏;

import java.util.Arrays;

/**
 * @Author mapKey
 * @Date 2022-09-07-2:25 PM
 */
class Solution1 {
    /* 主函数 */
    private int m;
    private int n;
    // 备忘录，消除重叠子问题
    int[][] memo;

    int calculateMinimumHP(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        // 备忘录中都初始化为 -1
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        /* 定义：从 (i, j) 到达右下角，需要的初始血量至少是多少 */
        return dp(grid, 0, 0);
    }


    /* 定义：从 (i, j) 到达右下角，需要的初始血量至少是多少 */
    int dp(int[][] grid, int i, int j) {

        // base case
        if (i == m - 1 && j == n - 1) {
            return grid[i][j] >= 0 ? 1 : -grid[i][j] + 1;
        }
        if (i == m || j == n) {
            return Integer.MAX_VALUE;
        }
        // 避免重复计算
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // 状态转移逻辑
        int res = Math.min(
                dp(grid, i, j + 1),
                dp(grid, i + 1, j)
        ) - grid[i][j];
        // 骑士的生命值至少为 1
        memo[i][j] = res <= 0 ? 1 : res;

        return memo[i][j];
    }
}

//数组迭代自底向上如下

class Solution2 {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m + 1][n + 1];
        /* 定义：从 (i, j) 到达右下角，需要的初始血量至少是多少 */
        dp[m - 1][n - 1] = dungeon[m - 1][n - 1] < 0 ? -dungeon[m - 1][n - 1] + 1 : 1;
        for (int i = m; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                if (i == m || j == n) {
                    dp[i][j] = Integer.MAX_VALUE;
                    continue;
                }
                if (i == m - 1 && j == n - 1) {
                    continue;
                }
                int res = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
                dp[i][j] = res <= 0 ? 1 : res;
            }
        }
        return dp[0][0];
    }
}

//状态压缩如下：
class Solution3 {
    public int calculateMinimumHPII(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[] dp = new int[n + 1];

        for (int i = m; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                if (i == m || j == n) {
                    dp[j] = Integer.MAX_VALUE;
                    continue;
                }
                if (i == m - 1 && j == n - 1) {
                    dp[j] = dungeon[i][j] < 0 ? -dungeon[i][j] + 1 : 1;
                    continue;
                }
                int res = Math.min(dp[j], dp[j + 1]) - dungeon[i][j];
                dp[j] = res <= 0 ? 1 : res;

            }
        }
        return dp[0];
    }
}


/*未解决所携带信息不够, 试图增加记录此时的血量,但情况太复杂*/
//class Solution {
//    public int calculateMinimumHP(int[][] dungeon) {
//        int m = dungeon.length;
//        int n = dungeon[0].length;
//        // 达到dp[i][j]所需的最少血量
//        int[][] dp = new int[m][n];
//        // 记录此时的血量
//        int cnt = 1;
//        if (dungeon[0][0] > 0) {
//            cnt += dungeon[0][0];
//            dp[0][0] = 1;
//        } else {
//            cnt -= dungeon[0][0];
//            dp[0][0] = cnt + dungeon[0][0];
//        }
//        for (int j = 1; j < n; j++) {
//
//            if (dungeon[0][j] < 0) {
//                cnt += dungeon[0][j];
//                dp[0][j] +=
//            }
//            dp[0][j] =
//        }
//    }
//}
