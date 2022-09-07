package ch10_环检测及拓扑排序算法.b_210课程表2;

import java.util.*;

/**
 * @Author mapKey
 * @Date 8/4/2022 11:31 AM
 * @Since version-1.0
 * @Description DFS拓扑排序
 */
class a_Solution {
    // 记录后序遍历结果
    List<Integer> postorder = new ArrayList<>();
    // 记录是否存在环
    boolean hasCycle = false;
    boolean[] visited, onPath;

    // 主函数
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        // 遍历图
        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }
        // 有环图无法进行拓扑排序
        if (hasCycle) {
            return new int[]{};
        }
        // 逆后序遍历结果即为拓扑排序结果
        Collections.reverse(postorder);
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = postorder.get(i);
        }
        //Object[] res = postorder.toArray();

        return res;
    }

    // 图遍历函数
    void traverse(List<Integer>[] graph, int s) {
        if (onPath[s]) {
            // 发现环
            hasCycle = true;
        }
        if (visited[s] || hasCycle) {
            return;
        }
        // 前序遍历位置
        onPath[s] = true;
        visited[s] = true;
        for (int adj : graph[s]) {
            traverse(graph, adj);
        }
        // 后序遍历位置
        postorder.add(s);
        onPath[s] = false;
    }

    // 建图函数
    List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        // 图中共有 numCourses 个节点
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];
            // 修完课程 from 才能修课程 to
            // 在图中添加一条从 from 指向 to 的有向边
            graph[from].add(to);
        }
        return graph;
    }

}

/**
 * @Author mapKey
 * @Date 8/4/2022 11:31 AM
 * @Since version-1.0
 * @Description BFS
 */

class b_Solution {
    // 主函数
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 建图，和环检测算法相同
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        // 计算入度，和环检测算法相同
        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            int from = edge[1], to = edge[0];
            indegree[to]++;
        }

        // 根据入度初始化队列中的节点，和环检测算法相同
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        // 记录拓扑排序结果
        int[] res = new int[numCourses];
        // 记录遍历节点的顺序（索引）
        int count = 0;
        // 开始执行 BFS 算法
        while (!q.isEmpty()) {
            int cur = q.poll();
            // 弹出节点的顺序即为拓扑排序结果
            res[count] = cur;
            count++;
            for (int next : graph[cur]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        if (count != numCourses) {
            // 存在环，拓扑排序不存在
            return new int[]{};
        }

        return res;
    }

    // 建图函数
    List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        // 图中共有 numCourses 个节点
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : prerequisites) {
            int from = edge[1], to = edge[0];
            // 修完课程 from 才能修课程 to
            // 在图中添加一条从 from 指向 to 的有向边
            graph[from].add(to);
        }
        return graph;
    }
}
// 详细解析参见：
// https://labuladong.github.io/article/?qno=210



/**
 * @Author mapKey
 * @Date 8/4/2022 3:24 PM
 * @Since version-1.0
 * @Description
 * mytest0
 */

class Solution1 {
    private boolean[] visited;
    private boolean[] onPath;
    private boolean hasCycle;

    //ArrayList<Integer> order = new ArrayList<>();
    int[] res;
    int count ;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        res = new int[numCourses];
        count = numCourses - 1;
        LinkedList<Integer>[] graph = buildGraph(numCourses, prerequisites);

        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }
        if (hasCycle) {
            return new int[]{};
        }


        //Collections.reverse(order);

        //for (int i = 0; i < numCourses; i++) {
        //    res[i] = order.get(i);
        //}

        return res;
    }

    private void traverse(LinkedList<Integer>[] graph, int s) {
        if (onPath[s]) {
            hasCycle = true;
        }

        if (hasCycle || visited[s]) {
            return;
        }
        visited[s] = true;
        onPath[s] = true;
        for (Integer adj : graph[s]) {
            traverse(graph, adj);
        }
        res[count--] = s;
        onPath[s] = false;
    }

    private LinkedList<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        LinkedList<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int[] pre : prerequisites) {
            int from = pre[1];
            int to = pre[0];
            graph[from].add(to);
        }
        return graph;
    }
}

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        LinkedList<Integer>[] graph = buildGraph(numCourses, prerequisites);
        Queue<Integer> q = new LinkedList<>();
        int[] inDegree = getInDegree(numCourses, prerequisites);
        int count = numCourses;
        int[] res = new int[numCourses];
        for (int v = 0; v < numCourses; v++) {
            if (inDegree[v] == 0) {
                q.offer(v);
            }
        }
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Integer cur = q.poll();
                res[count] = cur;
                count++;

                for (Integer adj : graph[cur]) {
                    inDegree[adj]--;
                    if (inDegree[adj] == 0) {
                        q.offer(adj);
                    }
                }
            }
        }

        if (count != numCourses) return new int[]{};


        return res;
    }

    private int[] getInDegree(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];

        for (int[] edge : prerequisites) {
            int to = edge[0];
            inDegree[to]++;
        }
        return inDegree;
    }

    private LinkedList<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        LinkedList<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];
            graph[from].add(to);
        }
        return graph;
    }
}