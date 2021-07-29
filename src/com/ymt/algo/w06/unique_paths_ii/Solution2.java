package com.ymt.algo.w06.unique_paths_ii;

/**
 * 思路:
 * m * n
 * 自底向上
 * 在某一位置有两个决策
 * 某个位置，可以来自➡️，也可以来自⬇️
 * dp[i][j] = dp[i][j+1] + dp[i+1][j]
 * <p>
 * i==m-1时，最后一行，只能用来自右侧的值
 * dp[i][j] = dp[i][j+1]
 * <p>
 * j==n-1时，最后一列，只能来自下侧的值
 * dp[i][j] = dp[i+1][j]
 *
 * @author mingtao
 * @date 2021/7/29 09:35
 */
public class Solution2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m][n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (obstacleGrid[i][j] == 1) {
                    //如果改点是障碍，无路可走
                    dp[i][j] = 0;
                } else if (i == m - 1 && j == n - 1) {
                    //起始点算一步
                    dp[i][j] = 1;
                } else if (i == m - 1) {
                    //只能从本行的左侧过来
                    dp[i][j] = dp[i][j + 1];
                } else if (j == n - 1) {
                    //只能从本列上侧过来
                    dp[i][j] = dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i][j + 1] + dp[i + 1][j];
                }
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] input = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};

        Solution2 solution = new Solution2();
        int paths = solution.uniquePathsWithObstacles(input);
        System.out.println(paths);
    }
}
