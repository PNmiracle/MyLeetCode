package c_96不同的二叉搜索树;

/**
 * @Author mapKey
 * @Date 2022-09-17-10:39 AM
 *
 *
 * 正宗的穷举问题
 * 二叉树算法的关键就在于明确根节点需要做什么，其实 BST 作为一种特殊的二叉树，核心思路也是一样的。
 *根据 BST 的特性，根节点的左子树都比根节点的值小，右子树的值都比根节点的值大。
 *
 */
class Solution1 {
    // 备忘录
    int[][] memo;

    int numTrees(int n) {
        // 备忘录的值初始化为 0
        memo = new int[n + 1][n + 1];
        // 计算闭区间 [1, n] 组成的 BST 个数
        return count(1, n);
    }
    // 定义：闭区间 [lo, hi] 的数字能组成 count(lo, hi) 种 BST
    int count(int lo, int hi) {
        if (lo > hi) return 1;
        // 查备忘录
        if (memo[lo][hi] != 0) {
            return memo[lo][hi];
        }

        int res = 0;
        // i 的值作为根节点 root
        for (int i = lo; i <= hi; i++) {
            int left = count(lo, i - 1);
            int right = count(i + 1, hi);
            // 左右子树的组合数乘积是 BST 的总数
            res += left * right;
        }
        // 将结果存入备忘录
        memo[lo][hi] = res;

        return res;
    }
}

class Solution {
    int[][] memo;
    public int numTrees(int n) {
        memo = new int[n + 1][n + 1];
        return count(1, n);
    }

    private int count(int lo, int hi) {
        if (lo > hi) {
            return 1;
        }
        if (memo[lo][hi] != 0) {
            return memo[lo][hi];
        }
        int res = 0;
        for (int i = lo; i <= hi; i++) {
            int left = count(lo, i - 1);
            int right = count(i + 1, hi);
            res += left * right;
        }
        memo[lo][hi] = res;
        return res;
    }
}