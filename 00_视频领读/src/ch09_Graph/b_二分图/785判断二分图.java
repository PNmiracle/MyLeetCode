package ch09_Graph.b_二分图;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author mapKey
 * @Date 8/3/2022 10:27 AM
 * @Since version-1.0
 * @Description 785. 判断二分图
 * https://labuladong.gitee.io/algo/2/22/52/#%E9%A2%98%E7%9B%AE%E5%AE%9E%E8%B7%B5
 */
class Solution1 {
    // 记录图是否符合二分图性质
    private boolean ok = true;
    // 记录图中节点的颜色，false 和 true 代表两种不同颜色
    private boolean[] color;
    // 记录图中节点是否被访问过
    private boolean[] visited;

    // 主函数，输入邻接表，判断是否是二分图
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        color = new boolean[n];
        visited = new boolean[n];
        // 因为图不一定是联通的，可能存在多个子图
        // 所以要把每个节点都作为起点进行一次遍历
        // 如果发现任何一个子图不是二分图，整幅图都不算二分图
        for (int v = 0; v < n; v++) {
            if (!visited[v]) {
                traverse(graph, v);
            }
        }
        return ok;
    }

    // DFS 遍历框架
    private void traverse(int[][] graph, int v) {
        // 如果已经确定不是二分图了，就不用浪费时间再递归遍历了
        if (!ok) return;

        visited[v] = true;
        for (int w : graph[v]) {
            if (!visited[w]) {
                // 相邻节点 w 没有被访问过
                // 那么应该给节点 w 涂上和节点 v 不同的颜色
                //color[w] = !color[w];注意不要写错
                color[w] = !color[v];
                // 继续遍历 w
                traverse(graph, w);
            } else {
                // 相邻节点 w 已经被访问过
                // 根据 v 和 w 的颜色判断是否是二分图
                if (color[w] == color[v]) {
                    // 若相同，则此图不是二分图
                    ok = false;
                    return;
                }
            }
        }
    }

}

/**
 * @Author mapKey
 * @Date 8/3/2022 10:36 AM
 * @Since version-1.0
 * @Description BFS
 */
class Solution2 {
    // 记录图是否符合二分图性质
    private boolean ok = true;
    // 记录图中节点的颜色，false 和 true 代表两种不同颜色
    private boolean[] color;
    // 记录图中节点是否被访问过
    private boolean[] visited;


    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        color = new boolean[n];
        visited = new boolean[n];

        for (int v = 0; v < n; v++) {
            if (!visited[v]) {
                // 改为使用 BFS 函数
                bfs(graph, v);
            }
        }

        return ok;
    }

    // 从 start 节点开始进行 BFS 遍历
    private void bfs(int[][] graph, int start) {
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.offer(start);

        while (!q.isEmpty() && ok) {
            int v = q.poll();
            // 从节点 v 向所有相邻节点扩散
            for (int w : graph[v]) {
                if (!visited[w]) {
                    // 相邻节点 w 没有被访问过
                    // 那么应该给节点 w 涂上和节点 v 不同的颜色
                    color[w] = !color[v];
                    // 标记 w 节点，并放入队列
                    visited[w] = true;
                    q.offer(w);
                } else {
                    // 相邻节点 w 已经被访问过
                    // 根据 v 和 w 的颜色判断是否是二分图
                    if (color[w] == color[v]) {
                        // 若相同，则此图不是二分图
                        ok = false;
                        return;
                    }
                }
            }
        }
    }

}

/**
 * @Author mapKey
 * @Date 8/3/2022 10:24 AM
 * @Since version-1.0
 * @Description mytest1
 */

class Solution3 {
    private boolean isTrue = true;
    private boolean[] visited;
    private boolean[] color;

    public boolean isBipartite(int[][] graph) {
        int len = graph.length;
        visited = new boolean[len];
        color = new boolean[len];
        for (int v = 0; v < len; v++) {
            if (!visited[v]) {
                traverse(graph, v);
            }
        }

        return isTrue;
    }

    private void traverse(int[][] graph, int v) {
        if (!isTrue) {
            return;
        }

        //visited[v] = true;
        for (int w : graph[v]) {
            if (!visited[w]) {
                //visited[w] = true; 与84行重复,89行决定不写这行
                visited[w] = true;
                color[w] = !color[v];
                traverse(graph, w);
            } else {
                if (color[v] == color[w]) {
                    isTrue = false;
                    return;
                }
            }
        }

    }
}
/**
 * @Author mapKey
 * @Date 8/3/2022 11:02 AM
 * @Since version-1.0
 * @Description
 * mytest2 BFS
 */
class Solution4 {
    private boolean ok = true;
    private boolean[] color;
    private boolean[] visited;
    private Queue<Integer> q = new LinkedList<>();

    public boolean isBipartite(int[][] graph) {
        int len = graph.length;
        color = new boolean[len];
        visited = new boolean[len];
        for (int v = 0; v < len; v++) {
            if (!visited[v]) {
                bfs(graph, v);
            }
        }

        return ok;
    }

    private void bfs(int[][] graph, int start) {
        if (!ok) {
            return;
        }
        q.offer(start);
        visited[start] = true;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int v = q.poll();

                for (int w : graph[v]) {
                    if (!visited[w]) {
                        color[w] = !color[v];
                        visited[w] = true;
                        q.offer(w);

                    } else {
                        if (color[w] == color[v]) {
                            ok = false;
                            return;
                        }
                    }
                }
            }
        }
    }
}