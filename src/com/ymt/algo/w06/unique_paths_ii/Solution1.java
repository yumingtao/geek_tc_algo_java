package com.ymt.algo.w06.unique_paths_ii;

/**
 * 思路:
 * m * n
 * 自顶向下
 * 在某一位置有两个决策
 * 某个位置，可以来自⬅️，也可以来自⬆️
 * dp[i][j] = dp[i][j-1] + dp[i-1][j]
 *
 * i==0时
 * dp[i][j] = dp[i-1][j]
 *
 * j==0时
 * dp[i][j] = dp[i][j-1]
 *
 * @author mingtao
 * @date 2021/7/29 09:35
 */
public class Solution1 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    //如果改点是障碍，无路可走
                    dp[i][j] = 0;
                } else if (i == 0 && j == 0) {
                    //起始点算一步
                    dp[i][j] = 1;
                } else if (i == 0) {
                    //只能从本行的左侧过来
                    dp[i][j] = dp[i][j - 1];
                } else if (j == 0) {
                    //只能从本列上侧过来
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] input = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};

        Solution1 solution = new Solution1();
        int paths = solution.uniquePathsWithObstacles(input);
        System.out.println(paths);
    }
}
