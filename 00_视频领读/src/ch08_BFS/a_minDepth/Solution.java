package ch08_BFS.a_minDepth;


import java.util.LinkedList;

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

/**
 * @Author mapKey
 * @Date 7/31/2022 4:07 PM
 * @Since version-1.0
 * @Description 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。

 */
class Framework {
  /*  // 计算从起点 start 到终点 target 的最近距离
    int BFS(Node start, Node target) {
        Queue<Node> q; // 核心数据结构
        Set<Node> visited; // 避免走回头路

        q.offer(start); // 将起点加入队列
        visited.add(start);
        int step = 0; // 记录扩散的步数

        while (q not empty) {
            int sz = q.size();
            *//* 将当前队列中的所有节点向四周扩散 *//*
            for (int i = 0; i < sz; i++) {
                Node cur = q.poll();
                *//* 划重点：这里判断是否到达终点 *//*
                if (cur is target)
                return step;
                *//* 将 cur 的相邻节点加入队列 *//*
                for (Node x : cur.adj()) {
                    if (x not in visited) {
                        q.offer(x);
                        visited.add(x);
                    }
                }
            }
            *//* 划重点：更新步数在这里 *//*
            step++;
        }
    }*/

}
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //辅助队列
        LinkedList<TreeNode> q = new LinkedList<>();
        q.offer(root);
        // root 本身就是一层，depth 初始化为 1
        int depth = 1;
        while (!q.isEmpty()) {
            /* 遍历当前层的节点 */
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode head = q.poll();
                /* 判断是否到达叶子结点 */
                if (head.left == null && head.right == null) {
                    return depth;
                }
                /* 将下一层节点加入队列 */
                if (head.left != null) {
                    q.add(head.left);
                }
                if (head.right != null) {
                    q.add(head.right);
                }
            }
            /* 这里增加步数 */
            depth++;
        }

        return depth;
    }
}


class Solution1 {
    public int minDepth(TreeNode root) {
        if (root == null) {
            //此处两难处理
            return Integer.MAX_VALUE - 1;

        }
        int minLeft = minDepth(root.left);
        int minRight = minDepth(root.right);
        int res = Math.min(minLeft, minRight) + 1;
        return res == Integer.MAX_VALUE ? 1 : res;
    }
}

class Solution2 {
    int res = Integer.MAX_VALUE;
    int depth = 0;

    public int minDepth(TreeNode root) {
        traverse(root);
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        depth++;
        if (root.left == null && root.right == null) {
            res = Math.min(res, depth);
        }
        traverse(root.left);
        //中序位置
        traverse(root.right);

        //后序位置
        depth--;
    }
}

