package ch08_BFS.b_openLock;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
class Solution1 {
    public int openLock(String[] deadends, String target) {
        // 记录需要跳过的死亡密码
        Set<String> deads = new HashSet<>();
        for (String s : deadends) deads.add(s);
        // 记录已经穷举过的密码，防止走回头路
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        // 从起点开始启动广度优先搜索
        int step = 0;
        q.offer("0000");
        visited.add("0000");

        while (!q.isEmpty()) {
            int sz = q.size();
            /* 将当前队列中的所有节点向周围扩散 */
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();

                /* 判断是否到达终点,以及是否跳出循环 */
                if (deads.contains(cur))
                    continue;
                if (cur.equals(target))
                    return step;

                /* 将一个节点的未遍历相邻节点加入队列 */
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        q.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        q.offer(down);
                        visited.add(down);
                    }
                }
            }
            /* 在这里增加步数 */
            step++;
        }
        // 如果穷举完都没找到目标密码，那就是找不到了
        return -1;
    }

    // 将 s[j] 向上拨动一次
    String plusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9')
            ch[j] = '0';
        else
            ch[j] += 1;
        return new String(ch);
    }

    // 将 s[i] 向下拨动一次
    String minusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0')
            ch[j] = '9';
        else
            ch[j] -= 1;
        return new String(ch);
    }
}

class Solution {
    public int openLock(String[] deadends, String target) {
        //HashSet<String> deads = new HashSet<>();
        //for (String s : deadends) {
        //    deads.add(s);
        //}
        HashSet<String> visited = new HashSet<>();
        for (String s : deadends) {
            visited.add(s);
        }
        visited.add("0000");
        LinkedList<String> q = new LinkedList<>();
        q.offer("0000");
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String head = q.poll();
                /*要有检验BFS起点是否合法的能力*/
                //if (deads.contains(head)) {
                //    continue;
                //}
                //if (visited.contains(head)) {
                //    continue;
                //}
                if (target.equals(head)) {
                    return step;
                }
                if (visited.contains(head)) {
                    continue;
                }

                for (int j = 0; j < 4; j++) {
                    String up = up(head, j);
                    if (!visited.contains(up)) {
                        visited.add(up);
                        q.offer(up);
                    }

                    String down = down(head, j);
                    if (!visited.contains(down)) {
                        visited.add(down);
                        q.offer(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private String down(String head, int j) {
        char[] chars = head.toCharArray();
        if (chars[j] == '0') {
            chars[j] = '9';
        } else {
            chars[j] -= 1;
        }
        return new String(chars);
    }

    private String up(String head, int j) {
        char[] chars = head.toCharArray();
        if (chars[j] == '9') {
            chars[j] = '0';
        } else {
            chars[j] += 1;
        }

        return new String(chars);
    }
}


