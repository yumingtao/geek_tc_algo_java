package com.ymt.algo.w03.longest_increasing_path_in_a_matrix;

/**
 * 思路:
 * 1.将其理解为一张有向五环图
 * 2.可以看成一个子问题：从任意一个点出发，向四个方向走，能够递增走多远，当前点能够走的最远步数为子问题最远步数+1
 * 3.针对计算过的点，直接返回值
 *
 * @author yumingtao
 * @date 7/12/21 2:55 PM
 */
public class DfsSolution {
    /**
     * 定义方向数组
     */
    private final int[] dx = new int[]{-1, 0, 0, 1};
    private final int[] dy = new int[]{0, -1, 1, 0};

    /**
     * 使用二维数组保存已经算过的最大步数
     * 因为到达一个格子，即使不能向4个方向走，步数也记为1，所以1可以表示未计算过
     */
    int[][] maxStep;

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int ans = 0;
        maxStep = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxStep[i][j] = 1;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, calculateStep(i, j, matrix));
            }
        }

        return ans;
    }

    private int calculateStep(int x, int y, int[][] matrix) {
        //如果计算过，则直接返回之前的计算结果
        if (maxStep[x][y] != 1) {
            return maxStep[x][y];
        }

        //主体处理逻辑
        //走到1个点，它有四个方向的出边
        int nx;
        int ny;
        for (int i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];
            //对于使用方向数组，要判断越界问题
            if (nx < 0 || ny < 0 || nx >= matrix.length || ny >= matrix[0].length) {
                continue;
            }
            //如果是递增的，则继续走，递归到下一层
            if (matrix[x][y] < matrix[nx][ny]) {
                //当前点的步数是子问题步数+1
                //比较当前点已经算过的步数和新计算出来的步数，保留最大值
                maxStep[x][y] = Math.max(maxStep[x][y], calculateStep(nx, ny, matrix) + 1);
            }
        }

        return maxStep[x][y];
    }
}
