package ch04最小路径和系列.b_64最小路径和;

import java.util.Arrays;

/**
 * @Author mapKey
 * @Date 2022-09-07-9:13 AM
 */
class Solution {
    private int[][] memo;

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        memo = new int[m][n];
        memo[0][0] = grid[0][0];
        for (int[] row : memo) {
            Arrays.fill(row, 66666);
        }
        int res = dp(grid, m - 1, n - 1);
        return res;
    }


    //从初始位置 到grid[i][j]的最小路径和
    private int dp(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return 99999;
        }

        if (memo[i][j] != 66666) {
            return memo[i][j];
        }

        //base case
        if (i == 0 && j == 0) {
            return grid[0][0];
        }

        memo[i][j] = Math.min(
                dp(grid, i - 1, j),
                dp(grid, i, j - 1)) + grid[i][j];
        return memo[i][j];
    }
}
