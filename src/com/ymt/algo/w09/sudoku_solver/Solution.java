package com.ymt.algo.w09.sudoku_solver;

/**
 * @author yumingtao
 * @date 2021/8/14 19:54
 */
public class Solution {
    public void solveSudoku(char[][] board) {
        dfs(board);
    }

    /**
     * 注意每次找一个空位置，然后遍历每个数字去判断合不合法
     * 而不要去枚举每个位置
     */
    private boolean dfs(char[][] board) {
        int[] emptyPosition = getOneEmptyPosition(board);
        int row = emptyPosition[0];
        int col = emptyPosition[1];
        //终止条件
        if (row == -1) {
            //说明已经没有空位置，填满了，直接返回
            return true;
        }

        //处理逻辑
        //循环当前空格可以填入的数字
        for (int num = 1; num <= 9; num++) {
            board[row][col] = (char) (num + 48);
            if (isValidSudoku(board) && dfs(board)) {
                return true;
            }

            //还原现场
            board[row][col] = '.';
        }

        //9个数字都循环完了
        return false;
    }

    /**
     * 获得一个空位置，返回包括行号，列号的长度为2的一维数组
     */
    private int[] getOneEmptyPosition(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    return new int[]{i, j};
                }
            }
        }

        //如果找不到，返回{-1，-1}，说明已经填满
        return new int[]{-1, -1};
    }

    private boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] cols = new int[9][9];
        int[][] boxes = new int[9][9];

        int boxIndex;
        int num;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                if (board[i][j] == '.') {
                    continue;
                }

                num = board[i][j] - '1';
                boxIndex = i / 3 * 3 + j / 3;
                if (!isValid(rows[i], num) || !isValid(cols[j], num) || !isValid(boxes[boxIndex], num)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isValid(int[] arr, int index) {
        if (arr[index] == 1) {
            return false;
        } else {
            arr[index] = 1;
        }
        return true;
    }
}
