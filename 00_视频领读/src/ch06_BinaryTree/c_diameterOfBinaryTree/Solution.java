package ch06_BinaryTree.c_diameterOfBinaryTree;

/**
 * @Author mapKey
 * @Date 7/26/2022 3:35 PM
 * @Since version-1.0
 * @Description
 * 543.
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

class Solution {
    int res = 0;

    //定义:返回直径长度
    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return res;
    }


    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //利用定义:计算左右子树的最大深度取最大值
        int maxLeft = maxDepth(root.left);
        int maxRight = maxDepth(root.right);
        //后序遍历位置,夹带私货,顺便把maxDiameter更新
        res = Math.max(res, maxLeft + maxRight);
        return Math.max(maxLeft, maxRight) + 1;
    }


}