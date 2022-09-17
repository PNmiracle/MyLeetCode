package ch12_旅游省钱大法加权最短路径.a_787K站中转内最便宜的航班;

import java.util.*;

class Solution1_dp {
    // 哈希表记录每个点的入度
    // to -> [from, price]
    HashMap<Integer, List<int[]>> indegree;
    int src, dst;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        // 将中转站个数转化成边的条数
        K++;
        this.src = src;
        this.dst = dst;

        indegree = new HashMap<>();
        for (int[] f : flights) {
            int from = f[0];
            int to = f[1];
            int price = f[2];
            // 记录谁指向该节点，以及之间的权重
            indegree.putIfAbsent(to, new LinkedList<>());
            indegree.get(to).add(new int[]{from, price});
        }

        return dp(dst, K);
    }


    // 定义：从 src 出发，k 步之内到达 s 的最短路径权重
    int dp(int s, int k) {
        // base case
        if (s == src) {
            return 0;
        }
        if (k == 0) {
            return -1;
        }
        // 初始化为最大值，方便等会取最小值
        int res = Integer.MAX_VALUE;
        if (indegree.containsKey(s)) {
            // 当 s 有入度节点时，分解为子问题
            for (int[] v : indegree.get(s)) {
                int from = v[0];
                int price = v[1];
                // 从 src 到达相邻的入度节点所需的最短路径权重
                int subProblem = dp(from, k - 1);
                // 跳过无解的情况
                if (subProblem != -1) {
                    res = Math.min(res, subProblem + price);
                }
            }
        }
        // 如果还是初始值，说明此节点不可达
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}

class Solution1_dp加memo {
    int src, dst;
    HashMap<Integer, List<int[]>> indegree;
    // 备忘录
    int[][] memo;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        K++;
        this.src = src;
        this.dst = dst;
        // 初始化备忘录，全部填一个特殊值
        memo = new int[n][K + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -888);
        }

        indegree = new HashMap<>();
        for (int[] f : flights) {
            int from = f[0];
            int to = f[1];
            int price = f[2];
            // 记录谁指向该节点，以及之间的权重
            indegree.putIfAbsent(to, new LinkedList<>());
            indegree.get(to).add(new int[]{from, price});
        }

        return dp(dst, K);
    }

    // 定义：从 src 出发，k 步之内到达 s 的最小成本
    int dp(int s, int k) {
        // base case
        if (s == src) {
            return 0;
        }
        if (k == 0) {
            return -1;
        }
        // 查备忘录，防止冗余计算
        if (memo[s][k] != -888) {
            return memo[s][k];
        }

        // 状态转移不变
        // 初始化为最大值，方便等会取最小值
        int res = Integer.MAX_VALUE;
        if (indegree.containsKey(s)) {
            // 当 s 有入度节点时，分解为子问题
            for (int[] v : indegree.get(s)) {
                int from = v[0];
                int price = v[1];
                // 从 src 到达相邻的入度节点所需的最短路径权重
                int subProblem = dp(from, k - 1);
                // 跳过无解的情况
                if (subProblem != -1) {
                    res = Math.min(res, subProblem + price);
                }
            }
        }

        // 存入备忘录
        memo[s][k] = res == Integer.MAX_VALUE ? -1 : res;
        return memo[s][k];
    }
}


class Solution1_自底向上 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // dp[i][j] 表示 经过 j个中转站到达 i 的最低费用
        //k+1+1 的原因是 将最后一个节点也理解成中转节点
        int[][] dp = new int[n][k + 2];
        //初始化 ， 到达所有节点的初始化为 最大值 ， 因为要求最低费用
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int j = 0; j < k + 2; j++) {
            dp[src][j] = 0;//任何src 到src 最小都是 0
        }

        for (int j = 1; j < k + 2; j++) {
            for (int[] flight : flights) {
                /*
                    flight[0] form
                    flight[1] to
                    flight[2] wight
                */
                //这一步防止后面的dp[flight[0]][j-1] + flight[2] 这一步出现 Integer.MAX_VALUE 参加运算 出现 数值溢出现象
                //也可以在初始化的时候 初始化成一个较大的数 不一定是Integer.MAX_VALUE
                if (dp[flight[0]][j - 1] == Integer.MAX_VALUE) {
                    continue;
                }
                dp[flight[1]][j] = Math.min(dp[flight[1]][j], dp[flight[0]][j - 1] + flight[2]);
            }
        }
        return dp[dst][k + 1] == Integer.MAX_VALUE ? -1 : dp[dst][k + 1];

    }
}


class Solution2_BFS_迪杰克斯拉 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        List<int[]>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : flights) {
            int from = edge[0];
            int to = edge[1];
            int price = edge[2];
            graph[from].add(new int[]{to, price});
        }

        // 启动 dijkstra 算法
        // 计算以 src 为起点在 k 次中转到达 dst 的最短路径
        K++;
        return dijkstra(graph, src, K, dst);
    }

    class State {
        // 图节点的 id
        int id;
        // 从 src 节点到当前节点的花费
        int costFromSrc;
        // 从 src 节点到当前节点经过的节点个数
        int nodeNumFromSrc;

        State(int id, int costFromSrc, int nodeNumFromSrc) {
            this.id = id;
            this.costFromSrc = costFromSrc;
            this.nodeNumFromSrc = nodeNumFromSrc;
        }
    }

    // 输入一个起点 src，计算从 src 到其他节点的最短距离
    int dijkstra(List<int[]>[] graph, int src, int k, int dst) {
        // 定义：从起点 src 到达节点 i 的最短路径权重为 distTo[i]
        int[] distTo = new int[graph.length];
        // 定义：从起点 src 到达节点 i 至少要经过 nodeNumTo[i] 个节点
        int[] nodeNumTo = new int[graph.length];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        Arrays.fill(nodeNumTo, Integer.MAX_VALUE);
        // base case
        distTo[src] = 0;
        nodeNumTo[src] = 0;

        // 优先级队列，costFromSrc 较小的排在前面
        Queue<State> pq = new PriorityQueue<>((a, b) -> {
            return a.costFromSrc - b.costFromSrc;
        });
        // 从起点 src 开始进行 BFS
        pq.offer(new State(src, 0, 0));

        while (!pq.isEmpty()) {
            State curState = pq.poll();
            int curNodeID = curState.id;
            int costFromSrc = curState.costFromSrc;
            int curNodeNumFromSrc = curState.nodeNumFromSrc;

            if (curNodeID == dst) {
                // 找到最短路径
                return costFromSrc;
            }
            if (curNodeNumFromSrc == k) {
                // 中转次数耗尽
                continue;
            }

            // 将 curNode 的相邻节点装入队列
            for (int[] neighbor : graph[curNodeID]) {
                int nextNodeID = neighbor[0];
                int costToNextNode = costFromSrc + neighbor[1];
                // 中转次数消耗 1
                int nextNodeNumFromSrc = curNodeNumFromSrc + 1;

                // 更新 dp table
                if (distTo[nextNodeID] > costToNextNode) {
                    distTo[nextNodeID] = costToNextNode;
                    nodeNumTo[nextNodeID] = nextNodeNumFromSrc;
                }
                // 剪枝，如果中转次数更多，花费还更大，那必然不会是最短路径
                if (costToNextNode > distTo[nextNodeID]
                        && nextNodeNumFromSrc > nodeNumTo[nextNodeID]) {
                    continue;
                }

                pq.offer(new State(nextNodeID, costToNextNode, nextNodeNumFromSrc));
            }
        }
        return -1;
    }
}