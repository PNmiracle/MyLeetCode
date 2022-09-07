package ch09_Graph.a_TraverseGraph;

import java.util.LinkedList;
import java.util.List;
/**
 * @Author mapKey
 * @Date 8/2/2022 10:43 AM
 * @Since version-1.0
 * @Description 
 * 797. 所有可能的路径
 * https://labuladong.gitee.io/algo/2/22/50/
 */
class Solution1 {
    // 记录所有路径
    List<List<Integer>> res = new LinkedList<>();
    //int[][]邻接表
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        LinkedList<Integer> path = new LinkedList<>();
        traverse(graph, 0, path);
        return res;
    }

    /* 图的遍历框架 */
    void traverse(int[][] graph, int s, LinkedList<Integer> path) {

        // 添加节点 s 到路径
        path.addLast(s);

        int n = graph.length;
        if (s == n - 1) {
            // 到达终点
            res.add(new LinkedList<>(path));
            path.removeLast();
            //此处不可丢
            return;
        }

        // 递归每个相邻节点
        for (int v : graph[s]) {
            traverse(graph, v, path);
        }

        // 从路径移出节点 s
        path.removeLast();
    }
}
// 详细解析参见：
// https://labuladong.github.io/article/?qno=797



/**
 * @Author mapKey
 * @Date 8/2/2022 10:44 AM
 * @Since version-1.0
 * @Description
 * myTry1
 */
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        LinkedList<Integer> path = new LinkedList<>();
        traverse(graph, path, 0);
        return res;
    }

    private void traverse(int[][] graph, LinkedList<Integer> path, int s) {
        int n = graph.length;
        path.addLast(s);
        if (s == n - 1) {

            res.add(new LinkedList<>(path));
            path.removeLast();
            return;
        }

        for (int adj : graph[s]) {
            traverse(graph, path, adj);
        }
        path.removeLast();

    }
}