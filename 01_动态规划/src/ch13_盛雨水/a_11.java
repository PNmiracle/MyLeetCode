package ch13_盛雨水;

/**
 * @Author mapKey
 * @Date 2023-03-31-7:56
 */
public class a_11 {
    class Solution {
        private int[][] dp;

        public int maxArea(int[] height) {
            int n = height.length;
            dp = new int[n][n];


            return dp[0][n - 1];
        }


    }

    class Solution2 {
        int[][] memo;//备忘录

        public int maxArea(int[] height) {
            int n = height.length;
            memo = new int[n][n];
            return dp(height, 0, n - 1);
        }
        //dp()函数的定义:仅仅使用第 i 到第 j 根柱子（包括它们）之间的柱子能得到的最大盛水量
        private int dp(int[] height, int i, int j) {
            if (i >= j || i >= height.length || j < 0) { //corner case
                return -1;
            }
            if (memo[i][j] != 0) {
                return memo[i][j]; //算过的就不要算啦
            }
            int res = (j - i) * Math.min(height[i], height[j]);
            if (height[i] < height[j]) { //有的选择可以不做, 能将O(n²)的时间复杂度降为O(n)
                memo[i][j] = Math.max(res, dp(height, i + 1, j));
            } else {
                memo[i][j] = Math.max(res, dp(height, i, j - 1));
            }
            return memo[i][j];
        }
    }
}
