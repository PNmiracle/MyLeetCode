package ch07子集背包问题.a_416分割等和子集;

/**
 * @Author mapKey
 * @Date 2022-09-09-8:57 AM
 */
class Solution_1_转化为0_1背包问题 {
    /***`dp[i][j] = x` 表示，对于前 `i` 个物品（`i` 从 1 开始计数），当前背包的容量为 `j` 时，若 `x` 为 `true`，则说明可以恰好将背包装满，若 `x` 为 `false`，则说明不能恰好将背包装满**。*/
    boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        // 和为奇数时，不可能划分成两个和相等的集合
        if (sum % 2 != 0) return false;
        int n = nums.length;
        sum = sum / 2;
        boolean[][] dp = new boolean[n + 1][sum + 1];
        // base case
        for (int i = 0; i <= n; i++)
            dp[i][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - nums[i - 1] < 0) {
                    // 背包容量不足，不能装入第 i 个物品
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 装入或不装入背包
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][sum];
    }
}

class Solution_1空间优化 {
    boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        // 和为奇数时，不可能划分成两个和相等的集合
        if (sum % 2 != 0) return false;
        int n = nums.length;
        sum = sum / 2;
        boolean[] dp = new boolean[sum + 1];

        // base case
        dp[0] = true;


        for (int i = 1; i <= n; i++) {
            for (int j = sum; j >= 0; j--) {
                if (j - nums[i - 1] >= 0) {
                    dp[j] = dp[j] || dp[j - nums[i - 1]];
                }
            }
        }
        return dp[sum];
    }
}
//唯一需要注意的是 `j` 应该从后往前反向遍历，因为每个物品（或者说数字）只能用一次，以免之前的结果影响其他的结果
/*1.先看原来2D情况，dp[i][j]的值，都是仅依靠上一行dp[i-1][...]得出的。意思是我们要算当前dp[i]行的值，仅需要上一行dp[i-1]就好。所以可以将其转化为：原地更新1D数组问题；

2.现在考虑降为1D，定义该1D数组为int[] dp。回忆原来2D情况，dp[i][j]的值都是依靠“其正上方的值dp[i-1][j]+左上方的值dp[i-1][j-nums[i]]”来更新。那么如果对1D进行正向遍历即从dp[0]->dp[n-1]填充，对于某一位例如dp[cur]的更新，势必会用到dp[pre]（pre<cur），因为是正向遍历，那么dp[pre]在当前轮次已经被更新过了，当在这种情况下计算的dp[cur]肯定不正确（其实说白了，就相当于2D情况下，使用了同一行的值。例如使用dp[i][j-nums[i]]来更新dp[i][j]）；

3.现在解释对1D数组进行反向遍历即从dp[n-1]->dp[0]填充。同样，对于某一位例如dp[cur]的更新，势必会用到dp[pre]（pre<cur）。但注意，因为是从后往前进行遍历的，此时dp[pre]在当前轮次未被更新，所以就相当于2D情况下使用的上一行的值，这样计算就是正确的了。*/

class Solution_正着遍历错误 {
    /***`dp[i][j] = x` 表示，对于前 `i` 个物品（`i` 从 1 开始计数），当前背包的容量为 `j` 时，若 `x` 为 `true`，则说明可以恰好将背包装满，若 `x` 为 `false`，则说明不能恰好将背包装满**。*/
    public boolean canPartition(int[] nums) {
        int sum = 0;
        int n = nums.length;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum /= 2;
        //boolean[][] dp = new boolean[n + 1][sum + 1];
        boolean[] dp = new boolean[sum + 1];
        // base case
        dp[0] = true;


        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - nums[i - 1] >= 0) {
                    dp[j] = dp[j] || dp[j - nums[i - 1]];
                }
            }
        }

        return dp[sum];
    }
}
