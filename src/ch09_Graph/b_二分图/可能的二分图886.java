package ch09_Graph.b_二分图;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author mapKey
 * @Date 8/3/2022 11:23 AM
 * @Since version-1.0
 * @Description
 */
class aa {

    private boolean ok = true;
    private boolean[] color;
    private boolean[] visited;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        // 图节点编号从 1 开始
        color = new boolean[n + 1];
        visited = new boolean[n + 1];
        // 转化成邻接表表示图结构
        List<Integer>[] graph = buildGraph(n, dislikes);

        for (int v = 1; v <= n; v++) {
            if (!visited[v]) {
                traverse(graph, v);
            }
        }
        return ok;
    }

    // 建图函数
    private List<Integer>[] buildGraph(int n, int[][] dislikes) {
        // 图节点编号为 1...n
        List<Integer>[] graph = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : dislikes) {
            int v = edge[1];
            int w = edge[0];
            // 「无向图」相当于「双向图」
            // v -> w
            graph[v].add(w);
            // w -> v
            graph[w].add(v);
        }
        return graph;
    }

    // 和之前判定二分图的 traverse 函数完全相同
    private void traverse(List<Integer>[] graph, int v) {
        if (!ok) return;
        visited[v] = true;
        for (int w : graph[v]) {
            if (!visited[w]) {
                color[w] = !color[v];
                traverse(graph, w);
            } else {
                if (color[w] == color[v]) {
                    ok = false;
                    return;
                }
            }
        }
    }

}
// 详细解析参见：
// https://labuladong.github.io/article/?qno=886

/**
 * @Author mapKey
 * @Date 8/3/2022 1:14 PM
 * @Since version-1.0
 * @Description
 * mytest1
 */
class mytest1 {
    private boolean ok = true;
    private boolean[] visited;
    private boolean[] color;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        visited = new boolean[n + 1];
        color = new boolean[n + 1];
        LinkedList<Integer>[] graph = build(n, dislikes);
        for (int v = 1; v <= n; v++) {
            if (!visited[v]) {
                traverse(graph, v);
            }
        }
        return ok;
    }

    private void traverse(LinkedList<Integer>[] graph, int v) {
        if (!ok) {
            return;
        }

        visited[v] = true;
        for (Integer w : graph[v]) {
            if (!visited[w]) {
                color[w] = !color[v];
                traverse(graph, w);
            } else {
                if (color[w] == color[v]) {
                    ok = false;
                    return;
                }
            }
        }

    }

    private LinkedList<Integer>[] build(int n, int[][] dislikes) {
        LinkedList<Integer>[] graph = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<Integer>();
        }
        for (int[] edge : dislikes) {
            int v = edge[0];
            int w = edge[1];
            graph[v].add(w);
            graph[w].add(v);
        }


        return graph;
    }
}
