package ch06_BinaryTree.a_maxDepth;
/**
 * @Author mapKey
 * @Date 7/26/2022 11:48 AM
 * @Since version-1.0
 * @Description 
 * 104. 二叉树的最大深度
 *
 * 但这里面大有玄妙，意味着前序位置的代码只能从函数参数中获取父节点传递来的数据
 * ，而后序位置的代码不仅可以获取参数数据，还可以获取到子树通过函数返回值传递回来的数据。
 */

public class TreeNode {
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

/*分解问题思路*/
class Solution1 {
    //明确定义:输入根节点,返回这棵二叉树的最大深度
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //利用定义:计算左右子树的最大深度取最大值
        int maxLeft = maxDepth(root.left);
        int maxRight = maxDepth(root.right);

        return Math.max(maxLeft, maxRight) + 1;
    }
}
/*遍历二叉树的思路*/
class Solution {
    //属性,当做外部变量
    int res = 0;
    int depth = 0;
    public int maxDepth(TreeNode root) {

        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        //corner case
        if (root == null) {
            return;
        }
        //前序位置
        depth++;
        //在前序位置更新结果
        if (root.left == null && root.right == null) {
            res = Math.max(res, depth);
        }
        traverse(root.left);
        //中序位置
        traverse(root.right);
        
        //后序位置
        depth--;
    }


}