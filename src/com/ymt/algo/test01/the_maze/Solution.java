package com.ymt.algo.test01.the_maze;

/**
 *
 * @author yumingtao
 * @date 2021/7/25 20:27
 */
public class Solution {
    public final int[] dx = {-1, 0, 0, 1};
    public final int[] dy = {0, 1, -1, 0};
    private final int DIRECTIONS = 4;

    //记录所有能使球停止的点
    private boolean[][] stopped;
    private int rows;
    private int cols;

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        //先得到行列号
        rows = maze.length;
        cols = maze[0].length;

        //初始化已访问数组，默认都是false
        stopped = new boolean[rows][cols];
        //标界开始点为已经访问过
        stopped[start[0]][start[1]] = true;

        /*dfs(start[0], start[1], maze, destination);
        return visited[destination[0]][destination[1]];*/

        return dfs(start[0], start[1], maze, destination);
    }

    private boolean dfs(int i, int j, int[][] maze, int[] destination) {
        //到达目标
        if (i == destination[0] && j == destination[1]) {
            return true;
        }

        //向四个方向
        for (int k = 0; k < DIRECTIONS; k++) {
            int ni = i;
            int nj = j;
            //System.out.println(String.format("ni + dx[k]: %s, nj + dy[k]r: %s", ni + dx[k], nj + dy[k]));
            //朝着一个方向走，直到遇到墙或是越界
            while (ni + dx[k] >= 0 && nj + dy[k] >= 0 && ni + dx[k] < rows && nj + dy[k] < cols
                    && maze[ni + dx[k]][nj + dy[k]] == 0) {
                ni += dx[k];
                nj += dy[k];
            }
            //System.out.println(String.format("ni: %s, nj: %s", ni, nj));

            //找到一个能让球停止的点，如果没有标记过则继续递归
            if (!stopped[ni][nj]) {
                stopped[ni][nj] = true;
                //dfs(ni, nj, maze, destination);
                boolean found = dfs(ni, nj, maze, destination);

                //如果找到了，就结束遍历
                if (found) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        //0 0 1 0 0
        //0 0 0 0 0
        //0 0 0 1 0
        //1 1 0 1 1
        //0 0 0 0 0
        //
        //输入 2: 起始位置坐标 (rowStart, colStart) = (0, 4)
        //输入 3: 目的地坐标 (rowDest, colDest) = (4, 4)
        int[][] input = new int[][]{{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        Solution solution = new Solution();
        boolean found = solution.hasPath(input, new int[]{0, 4}, new int[]{3, 2});
        System.out.println(found);

        /*found = solution.hasPath(input, new int[]{0, 4}, new int[]{3, 2});
        System.out.println(found);

        found = solution.hasPath(input, new int[]{0, 4}, new int[]{1, 2});
        System.out.println(found);

        found = solution.hasPath(input, new int[]{0, 4}, new int[]{0, 0});
        System.out.println(found);*/
    }
}