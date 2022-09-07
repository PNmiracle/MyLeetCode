package ch06_BinaryTree.b_preorderTraversal;

import java.util.ArrayList;
import java.util.List;
/**
 * @Author mapKey
 * @Date 7/26/2022 3:35 PM
 * @Since version-1.0
 * @Description
 * 144.
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
    //ArrayList<Integer> res = new ArrayList<>();
    //定义:输入一个节点,返回改节点为根的二叉树的前序遍历结果
    public List<Integer> preorderTraversal(TreeNode root) {
        //不做外部变量
        ArrayList<Integer> res = new ArrayList<>();

        if (root == null) {
            return res;
        }
        // 前序遍历结果特点：第一个是根节点的值，接着是左子树，最后是右子树
        res.add(root.val);
        res.addAll(preorderTraversal(root.left));
        res.addAll(preorderTraversal(root.right));

        return res;
    }
}

class Solution {

    ArrayList<Integer> res = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        traverse(root);

        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        traverse(root.left);
        traverse(root.right);
    }
}