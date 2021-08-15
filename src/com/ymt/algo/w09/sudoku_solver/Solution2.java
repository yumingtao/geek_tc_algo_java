package com.ymt.algo.w09.sudoku_solver;

/**
 * 优化思路：
 * 1.每次找到剩余的board中需要填入最少点的位置开始
 *
 * @author yumingtao
 * @date 2021/8/15 12:58
 */
public class Solution2 {
    //默认都是0表示可用
    int[][] rows = new int[9][9];
    int[][] cols = new int[9][9];
    int[][] boxes = new int[9][9];

    public void solveSudoku(char[][] board) {
        //初始化数组
        int index;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //将非'.'的部分设置为不可用
                if (board[i][j] != '.') {
                    index = board[i][j] - '1';
                    rows[i][index] = 1;
                    cols[j][index] = 1;
                    boxes[i / 3 * 3 + j / 3][index] = 1;
                }
            }
        }
        dfs(board);
    }

    /**
     * 注意每次找一个空位置，然后遍历每个数字去判断合不合法
     * 而不要去枚举每个位置
     */
    private boolean dfs(char[][] board) {
        int[] emptyPosition = getLeastValidEmptyPosition(board);
        int i = emptyPosition[0];
        int j = emptyPosition[1];

        //终止条件，填满，有解，返回
        if (i == -1) {
            return true;
        }

        //处理逻辑
        int index;
        int boxIndex = i / 3 * 3 + j / 3;
        for (int num = 1; num <= 9; num++) {
            index = num - 1;
            if (rows[i][index] == 0 && cols[j][index] == 0 && boxes[boxIndex][index] == 0) {
                rows[i][index] = 1;
                cols[j][index] = 1;
                boxes[boxIndex][index] = 1;
                board[i][j] = (char) (num + 48);
                if (dfs(board)) {
                    return true;
                } else {
                    //还原,继续尝试下一个数字
                    board[i][j] = '.';
                    rows[i][index] = 0;
                    cols[j][index] = 0;
                    boxes[boxIndex][index] = 0;
                }
            }
        }

        return false;
    }

    /**
     * 获得一个空位置,需要填入的有效数字最少，返回包括行号，列号的长度为2的一维数组
     */
    private int[] getLeastValidEmptyPosition(char[][] board) {
        int count = 0;
        int ansCount = 10;
        int boxIndex;
        int index;
        int[] result = new int[]{-1, -1};

        //循环遍历所有的位置，找到一个可以填入数字最少的位置做为起始点，然后进行bfs
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //找到一个没有数字的点，然后遍历所有的可能数字
                //最终找到当前board中，可以填入可能数字最少数字的一个
                if (board[i][j] == '.') {
                    boxIndex = i / 3 * 3 + j / 3;
                    count = 0;
                    for (int num = 1; num <= 9; num++) {
                        //确保数组不会越界
                        index = num - 1;
                        if (rows[i][index] == 0 && cols[j][index] == 0 && boxes[boxIndex][index] == 0) {
                            count++;
                        }
                    }

                    if (count < ansCount) {
                        ansCount = count;
                        result = new int[]{i, j};
                    }
                }
            }
        }

        //如果找不到，返回{-1，-1}，说明已经填满
        //System.out.println("i:" + result[0] + ",j:" + result[1]);
        return result;
    }
}
