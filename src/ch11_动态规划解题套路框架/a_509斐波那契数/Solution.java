package ch11_动态规划解题套路框架.a_509斐波那契数;

class a_Solution {
    public int fib(int n) {
        int[] memo = new int[n + 1];

        return helper(memo, n);
    }

    private int helper(int[] memo, int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = helper(memo, n - 1) + helper(memo, n - 2);
        return memo[n];
    }
}


class b_Solution {
    public int fib(int n) {
        //base case
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}


class Solution {
    public int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        int dp = 0;
        int dp_2 = 0, dp_1 = 1;
        for (int i = 2; i <= n; i++) {
            dp = dp_2 + dp_1;
            dp_2 = dp_1;
            dp_1 = dp;
        }

        return dp;
    }
}