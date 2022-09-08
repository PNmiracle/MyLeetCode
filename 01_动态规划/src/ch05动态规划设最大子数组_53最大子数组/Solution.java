package ch05动态规划设最大子数组_53最大子数组;

/**
 * @Author mapKey
 * @Date 2022-09-08-6:22 PM
 */
class Solution1_动态规划 {
    int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        // 定义：dp[i] 记录以 nums[i] 为结尾的「最大子数组和」
        int[] dp = new int[n];
        // base case
        // 第一个元素前面没有子数组
        dp[0] = nums[0];
        // 状态转移方程
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
        }
        // 得到 nums 的最大子数组
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}

class Solution2_空间压缩优化 {
    int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        // base case
        int dp_0 = nums[0];
        int dp_1 = 0, res = dp_0;

        for (int i = 1; i < n; i++) {
            // dp[i] = max(nums[i], nums[i] + dp[i-1])
            dp_1 = Math.max(nums[i], nums[i] + dp_0);
            dp_0 = dp_1;
            // 顺便计算最大的结果
            res = Math.max(res, dp_1);
        }

        return res;
    }
}


class Solution3_前缀和 {
    // 前缀和技巧解题
    int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        // 构造 nums 的前缀和数组
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        int res = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            // 维护 minVal 是 preSum[0..i] 的最小值
            minVal = Math.min(minVal, preSum[i]);
            // 以 nums[i] 结尾的最大子数组和就是 preSum[i+1] - min(preSum[0..i])
            res = Math.max(res, preSum[i + 1] - minVal);
        }
        return res;
    }
}

class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        //以nums[i]为结尾的连续子数组的最大和 = preSum[i + 1] - min(preSum[0..i])
        int res = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            //for (int j = 0; j < i; j++) {
            //    minVal = Math.min(minVal, preSum[i]);
            //}
            minVal = Math.min(minVal, preSum[i]);

            res = Math.max(res, preSum[i + 1] - minVal);
        }
        return res;
    }
}
