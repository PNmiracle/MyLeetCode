package ch06_BinaryTree.f_flatten;

import sun.reflect.generics.tree.Tree;

/**
 * @Author mapKey
 * @Date 7/26/2022 4:11 PM
 * @Since version-1.0
 * @Description 114. 二叉树展开为链表
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
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;
        root.right = left;
        TreeNode p = root;
        //TreeNode pre = root;
        while (p.right != null) {
            p = p.right;
            //pre = p;
            //p = p.right;
        }

        p.right = right;

    }
}