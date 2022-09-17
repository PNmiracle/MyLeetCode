package b_95不同的二叉搜索树2;

import javafx.util.Builder;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author mapKey
 * @Date 2022-09-17-10:29 AM
 *
 * 1、穷举 `root` 节点的所有可能。
 * 2、递归构造出左右子树的所有有效 BST。
 * 3、给 `root` 节点穷举所有左右子树的组合。
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
class Solution1 {
    /* 主函数 */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new LinkedList<>();
        // 构造闭区间 [1, n] 组成的 BST
        return build(1, n);
    }

    /* 构造闭区间 [lo, hi] 组成的 BST */
    List<TreeNode> build(int lo, int hi) {
        List<TreeNode> res = new LinkedList<>();
        // base case
        if (lo > hi) {
            res.add(null);
            return res;
        }

        // 1、穷举 root 节点的所有可能。
        for (int i = lo; i <= hi; i++) {
            // 2、递归构造出左右子树的所有合法 BST。
            List<TreeNode> leftTree = build(lo, i - 1);
            List<TreeNode> rightTree = build(i + 1, hi);
            // 3、给 root 节点穷举所有左右子树的组合。
            for (TreeNode left : leftTree) {
                for (TreeNode right : rightTree) {
                    // i 作为根节点 root 的值
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }
}


class Solution {
    public List<TreeNode> generateTrees(int n) {
        return build(1, n);
    }

    private List<TreeNode> build(int lo, int hi) {
        LinkedList<TreeNode> res = new LinkedList<>();
        if (lo > hi) {
            res.add(null);
            return res;
        }


        for (int i = lo; i <= hi; i++) {
            List<TreeNode> leftList = build(lo, i - 1);
            List<TreeNode> rightList = build(i + 1, hi);
            for (TreeNode left : leftList) {
                for (TreeNode right : rightList) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    right.right = right;
                    res.add(root);
                }
            }
        }


        return res;
    }
}

