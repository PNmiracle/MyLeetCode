package a_94二叉树的中序遍历;

/**
 * @Author mapKey
 * @Date 2022-09-17-10:07 AM
 */




import java.util.LinkedList;
import java.util.List;




class Solution1 {
    private List<Integer> res = new LinkedList<>();


    public List<Integer> inorderTraversal(TreeNode root) {
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        traverse(root.left);
        res.add(root.val);
        traverse(root.right);
    }
}

/* 动态规划思路 */
/*状态:当前root, 选择: 左右子节点
* 没有重叠子问题,不需要memo
*
* */
// 定义：输入一个节点，返回以该节点为根的二叉树的中序遍历结果
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        res.addAll(inorderTraversal(root.left));
        res.add(root.val);
        res.addAll(inorderTraversal(root.right));
        return res;
    }
}