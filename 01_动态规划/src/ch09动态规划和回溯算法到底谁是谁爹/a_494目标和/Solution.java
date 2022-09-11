package ch09动态规划和回溯算法到底谁是谁爹.a_494目标和;

import java.util.HashMap;

/**
 * @Author mapKey
 * @Date 2022-09-10-1:44 PM
 */
class Solution1_第一次尝试失败 {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;

        //dp[i][j] 前i个(从1开始)数, 和为j的表达式的数目
        int[][] dp = new int[n + 1][target + 1];

        //dp[i][j] = dp[i - 1][j - nums[i - 1]] + dp[i - 1][j + nums[i - 1]]
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] + dp[i - 1][j + nums[i - 1]];
                }
            }
        }
        return dp[n][target];
    }
}


class Solution2_回溯 {
    int result = 0;

    /* 主函数 */
    int findTargetSumWays(int[] nums, int target) {
        if (nums.length == 0) return 0;
        backtrack(nums, 0, target);
        return result;
    }

    /* 回溯算法模板 */
    /*def backtrack(路径, 选择列表):
    if 满足结束条件:
        result.add(路径)
        return

    for 选择 in 选择列表:
        做选择
        backtrack(路径, 选择列表)
        撤销选择*/
    void backtrack(int[] nums, int i, int remain) {
        // base case
        if (i == nums.length) {
            if (remain == 0) {
                // 说明恰好凑出 target
                result++;
            }
            return;
        }
        // 给 nums[i] 选择 - 号
        remain += nums[i];
        // 穷举 nums[i + 1]
        backtrack(nums, i + 1, remain);
        // 撤销选择
        remain -= nums[i];

        // 给 nums[i] 选择 + 号
        remain -= nums[i];
        // 穷举 nums[i + 1]
        backtrack(nums, i + 1, remain);
        // 撤销选择
        remain += nums[i];
    }
}

class Solution2_回溯消除重叠子问题成为了dp {
    int findTargetSumWays(int[] nums, int target) {
        if (nums.length == 0) return 0;
        return dp(nums, 0, target);
    }

    // 备忘录
    HashMap<String, Integer> memo = new HashMap<>();

    int dp(int[] nums, int i, int remain) {
        // base case
        if (i == nums.length) {
            if (remain == 0) return 1;
            return 0;
        }
        // 把它俩转成字符串才能作为哈希表的键
        String key = i + "," + remain;
        // 避免重复计算
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        // 还是穷举
        int result = dp(nums, i + 1, remain - nums[i]) + dp(nums, i + 1, remain + nums[i]);
        // 记入备忘录
        memo.put(key, result);
        return result;
    }
}


/***
 其实，这个问题可以转化为一个子集划分问题，而子集划分问题又是一个典型的背包问题。
 动态规划总是这么玄学，让人摸不着头脑……

 首先，如果我们把 `nums` 划分成两个子集 `A` 和 `B`，分别代表分配 `+` 的数和分配 `-` 的数，
 那么他们和 `target` 存在如下关系：

 sum(A) - sum(B) = target
 sum(A) = target + sum(B)
 sum(A) + sum(A) = target + sum(B) + sum(A)
 2 * sum(A) = target + sum(nums)
 ```

 综上，可以推出 `sum(A) = (target + sum(nums)) / 2`，
 也就是把原问题转化成：`nums` 中存在几个子集 `A`，
 使得 `A` 中元素的和为 `(target + sum(nums)) / 2`？
 */
class Solution3_转化为动态规划背包问题 {
    int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int n : nums) sum += n;
        // 这两种情况，不可能存在合法的子集划分
        if (sum < Math.abs(target) || (sum + target) % 2 == 1) {
            return 0;
        }
        return subsets(nums, (sum + target) / 2);
    }

    /* 计算 nums 中有几个子集的和为 sum */
    int subsets(int[] nums, int sum) {
        int n = nums.length;
        int[][] dp = new int[n + 1][sum + 1];
        //  base case
        // dp[0][1...W] = 0
        // nums中可能有 0
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if (j >= nums[i - 1]) {
                    // 两种选择的结果之和
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                } else {
                    // 背包的空间不足，只能选择不装物品 i
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][sum];
    }
}

class Solution3_dp空间优化 {

    int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int n : nums) sum += n;
        // 这两种情况，不可能存在合法的子集划分
        //if ((sum + target) % 2 == 1) {
        if (sum < Math.abs(target) || (sum + target) % 2 == 1) {
            return 0;
        }
        return subsets(nums, (sum + target) / 2);
    }

    /* 计算 nums 中有几个子集的和为 sum */
    int subsets(int[] nums, int sum) {
        int n = nums.length;
        int[] dp = new int[sum + 1];
        // base case
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            // j 要从后往前遍历
            /***对照二维 `dp`，只要把 `dp` 数组的第一个维度全都去掉就行了，唯一的区别就是这里的 `j` 要从后往前遍历，原因如下**：

             因为二维压缩到一维的根本原理是，`dp[j]` 和 `dp[j-nums[i-1]]` 还没被新结果覆盖的时候，相当于二维 `dp` 中的 `dp[i-1][j]` 和 `dp[i-1][j-nums[i-1]]`。

             那么，我们就要做到：**在计算新的 `dp[j]` 的时候，`dp[j]` 和 `dp[j-nums[i-1]]` 还是上一轮外层 for 循环的结果**。

             如果你从前往后遍历一维 `dp` 数组，`dp[j]` 显然是没问题的，但是 `dp[j-nums[i-1]]` 已经不是上一轮外层 for 循环的结果了，这里就会使用错误的状态，当然得不到正确的答案。

             现在，这道题算是彻底解决了。*/
            for (int j = sum; j >= 0; j--) {
                // 状态转移方程
                if (j >= nums[i - 1]) {
                    dp[j] = dp[j] + dp[j - nums[i - 1]];
                }
            }
        }
        return dp[sum];
    }
}


class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < Math.abs(target) || (sum + target) % 2 == 1) {
            return 0;
        }
        sum = (sum + target) / 2;
        return subsets(nums, sum);
    }

    private int subsets(int[] nums, int W) {
        int n = nums.length;
        int[] dp = new int[W + 1];
        //int[][] dp = new int[n + 1][W + 1];
        //base case
        // dp[0][1...W] = 0
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = W; j >= 0; j--) {
                if (j - nums[i - 1] >= 0) {
                    dp[j] = dp[j] + dp[j - nums[i - 1]];
                }
            }
        }

        return dp[W];
    }
}