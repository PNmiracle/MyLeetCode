package ch10_环检测及拓扑排序算法.a_207课程表;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author mapKey
 * @Date 8/4/2022 10:50 AM
 * @Since version-1.0
 * @Description DFS环检测
 */
class a_Solution {
    // 记录一次 traverse 递归经过的节点
    boolean[] onPath;
    // 记录遍历过的节点，防止走回头路
    boolean[] visited;
    // 记录图中是否有环
    boolean hasCycle = false;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);

        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        //可能为非连通图
        for (int i = 0; i < numCourses; i++) {
            // 遍历图中的所有节点
            traverse(graph, i);
        }
        // 只要没有循环依赖可以完成所有课程
        return !hasCycle;
    }

    void traverse(List<Integer>[] graph, int s) {
        if (onPath[s]) {
            // 出现环
            hasCycle = true;
        }

        if (visited[s] || hasCycle) {
            // 如果已经找到了环，也不用再遍历了
            return;
        }
        // 前序遍历代码位置
        visited[s] = true;
        onPath[s] = true;
        for (int t : graph[s]) {
            traverse(graph, t);
        }
        // 后序遍历代码位置
        onPath[s] = false;
    }

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


// 详细解析参见：
// https://labuladong.github.io/article/?qno=207

/**
 * @Author mapKey
 * @Date 8/5/2022 7:10 AM
 * @Since version-1.0
 * @Description BFS
 */
class b_Solution {
    // 主函数
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 建图，有向边代表「被依赖」关系
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        // 构建入度数组
        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            int from = edge[1], to = edge[0];
            // 节点 to 的入度加一
            indegree[to]++;
        }

        // 根据入度初始化队列中的节点
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                // 节点 i 没有入度，即没有依赖的节点
                // 可以作为拓扑排序的起点，加入队列
                q.offer(i);
            }
        }

        // 记录遍历的节点个数
        int count = 0;
        // 开始执行 BFS 循环
        while (!q.isEmpty()) {
            // 弹出节点 cur，并将它指向的节点的入度减一
            int cur = q.poll();
            count++;
            for (int next : graph[cur]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    // 如果入度变为 0，说明 next 依赖的节点都已被遍历
                    q.offer(next);
                }
            }
        }

        // 如果所有节点都被遍历过，说明不成环
        return count == numCourses;
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

/**
 * @Author mapKey
 * @Date 8/4/2022 11:01 AM
 * @Since version-1.0
 * @Description mytest1
 */
class Solution1 {
    private boolean[] onPath;
    private boolean[] visited;
    private boolean hasCycle;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        onPath = new boolean[numCourses];
        visited = new boolean[numCourses];
        LinkedList<Integer>[] graph = buildGraph(numCourses, prerequisites);

        for (int i = 0; i < numCourses; i++) {
            traverse(graph, 0);
        }
        return !hasCycle;
    }

    private void traverse(LinkedList<Integer>[] graph, int s) {
        if (onPath[s]) {
            hasCycle = true;
        }
        if (visited[s] || hasCycle) {
            return;
        }

        onPath[s] = true;
        visited[s] = true;
        for (Integer adj : graph[s]) {
            traverse(graph, adj);
        }
        onPath[s] = false;
    }

    private LinkedList<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        LinkedList<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<Integer>();
        }

        for (int[] pre : prerequisites) {
            int from = pre[1];
            int to = pre[0];
            graph[from].add(to);
        }
        return graph;

    }
}

/**
 * @Author mapKey
 * @Date 8/5/2022 7:24 AM
 * @Since version-1.0
 * @Description test2
 */
class Solution {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        LinkedList<Integer>[] graph = buildGraph(numCourses, prerequisites);
        int[] inDegree = getInDegree(numCourses, prerequisites);
        Queue<Integer> q = new LinkedList<>();
        for (int v = 0; v < inDegree.length; v++) {
            if (inDegree[v] == 0) {
                q.offer(v);
            }
        }
        int count = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Integer cur = q.poll();
                count++;
                for (Integer adj : graph[cur]) {
                    inDegree[adj]--;
                    if (inDegree[adj] == 0) {
                        q.add(adj);
                    }
                }
            }
        }

        return count == numCourses;
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