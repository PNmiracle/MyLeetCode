package ch03子序列问题解题模板;

/**
 * @Author mapKey
 * @Date 2022-09-07-8:13 AM
 */

class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        //目标dp[0][n - 1]
        //dp[i][j]返回[i..j]成为回文串的最少错作数
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {

                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
                }
            }
        }

        return dp[0][n - 1];
    }
}