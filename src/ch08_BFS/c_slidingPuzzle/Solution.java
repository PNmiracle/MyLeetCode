package ch08_BFS.c_slidingPuzzle;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
/**
 * @Author mapKey
 * @Date 8/2/2022 10:43 AM
 * @Since version-1.0
 * @Description
 * 773. 滑动谜题
 */
class Solution1 {
    public int slidingPuzzle(int[][] board) {
        int m = 2, n = 3;
        StringBuilder sb = new StringBuilder();
        String target = "123450";
        // 将 2x3 的数组转化成字符串作为 BFS 的起点
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]);
            }
        }
        String start = sb.toString();

        // 记录一维字符串的相邻索引
        int[][] neighbor = new int[][]{
                {1, 3},
                {0, 4, 2},
                {1, 5},
                {0, 4},
                {3, 1, 5},
                {4, 2}
        };

        /******* BFS 算法框架开始 *******/
        Queue<String> q = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        // 从起点开始 BFS 搜索
        q.offer(start);
        visited.add(start);

        int step = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();
                // 判断是否达到目标局面
                if (target.equals(cur)) {
                    return step;
                }
                // 找到数字 0 的索引
                int idx = 0;
                for (; cur.charAt(idx) != '0'; idx++) ;
                // 将数字 0 和相邻的数字交换位置
                for (int adj : neighbor[idx]) {
                    String new_board = swap(cur.toCharArray(), adj, idx);
                    // 防止走回头路
                    if (!visited.contains(new_board)) {
                        q.offer(new_board);
                        visited.add(new_board);
                    }
                }
            }
            step++;
        }
        /******* BFS 算法框架结束 *******/
        return -1;
    }

    private String swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }

}








/**
 * @Author mapKey
 * @Date 8/2/2022 9:45 AM
 * @Since version-1.0
 * @Description
 *
 */
class Solution {
    public int slidingPuzzle(int[][] board) {
        int m = 2, n = 3;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]);
            }
        }
        String start = sb.toString();
        String target = "123450";
        int[][] neighbour = {
                {1, 3},
                {0, 2, 4},
                {1, 5},
                {0, 4},
                {1, 3, 5},
                {2, 4}
        };

        HashSet<String> visited = new HashSet<>();
        LinkedList<String> q = new LinkedList<>();
        int step = 0;
        q.offer(start);
        visited.add(start);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.poll();

                if (target.equals(cur)) {
                    return step;
                }
                int idx = 0;
                char[] chars = cur.toCharArray();
                for (; chars[idx] != '0'; idx++) ;
                for (int j = 0; j < neighbour[idx].length; j++) {
                    String swapStr = swap(chars, neighbour, j, idx);

                    if (!visited.contains(swapStr)) {
                        q.offer(swapStr);
                        visited.add(swapStr);
                    }
                }
            }
            step++;
        }

        return -1;
    }

    private String swap(char[] chars, int[][] neighbour, int j, int idx) {
        int index = neighbour[idx][j];
        char temp = chars[idx];
        chars[idx] = chars[index];
        chars[index] = temp;
        return new String(chars);
    }
}