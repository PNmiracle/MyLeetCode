package ch07_BackTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author mapKey
 * @Date 7/27/2022 11:32 AM
 * @Since version-1.0
 * @Description 51. N 皇后
 */
class Solution {
    private ArrayList<String> track;
    private int n;
    private List<List<String>> res;

    public List<List<String>> solveNQueens(int n) {
        //return null;
        this.n = n;
        track = new ArrayList<>();
        res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        backtrack(board, 0);
        return res;
    }

    private void backtrack(char[][] board, int row) {
        if (track.size() == n) {
            res.add(new ArrayList<>(track));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (!isValid(board, row, col)) {
                continue;
            }
            board[row][col] = 'Q';
            track.add(new String(board[row]));

            backtrack(board, row + 1);

            board[row][col] = '.';
            track.remove(track.size() - 1);

        }

    }

    private boolean isValid(char[][] board, int row, int col) {
        for (int i = 0; i < n; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
}


class Solution1 {
    //路径,目标:记录可行的棋盘字符串,过程中:
    private List<String> track;
    //n == board.length
    private int n;
    //记录所有可行解
    private List<List<String>> res;
    private char[][] board;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        track = new ArrayList<>();
        res = new ArrayList<>();
        //n和char[][] board的赋值顺序,
        board = new char[n][n];
        // 初始化棋盘
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        // 从第 0 行开始
        backtrack(0);
        return res;
    }

    private void backtrack(int row) {
        // 满足要求
        if (track.size() == n) {
            //Java传的是引用,在回溯树的叶子结点处备份一个list
            res.add(new ArrayList<>(track));
            return;
        }
        for (int col = 0; col < board.length; col++) {
            // 该格(选择)不符合放棋子的条件
            if (!isValid(board, row, col)) continue;
            // 放棋子
            board[row][col] = 'Q';
            // 记录当前行的数据
            track.add(new String(board[row]));

            // 处理下一行
            backtrack(row + 1);

            // 移除棋子
            board[row][col] = '.';
            // 去除当前行的数据
            track.remove(track.size() - 1);
        }
    }

    boolean isValid(char[][] board, int row, int col) {
        //只要考虑以下不合理的情况即可

        // 检查列是否有皇后互相冲突
        for (int i = 0; i < n; i++) {
            if (board[i][col] == 'Q')
                return false;
        }
        // 检查右上方是否有皇后互相冲突
        for (int i = row - 1, j = col + 1;
             i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q')
                return false;
        }
        // 检查左上方是否有皇后互相冲突
        for (int i = row - 1, j = col - 1;
             i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q')
                return false;
        }
        return true;
    }
}