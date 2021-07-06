package com.ymt.algo.w03.num_of_islands;

/**
 * 思路：
 * 1.找到一个值为1的点，标记为访问过
 * 2.从这个值1的点向4个方向走，如果遇到1继续往下走，同时标记为已访问过，遇到0的时候返回
 * 3.这样可以找到某一个值为1的点，可以到达的所有为1的点
 * 4.继续找下一个没有访问过的1开始访问
 * 5.可以DFS或BFS实现
 * 6.从主函数进入DFS或BFS的次数即为岛屿数量
 * <p>
 * 本例使用DFS
 *
 * @author yumingtao
 * @date 7/6/21 10:26 AM
 */
public class DfsSolution {
    public static final int[] dx = {-1, 0, 0, 1};
    public static final int[] dy = {0, 1, -1, 0};
    private static int DIRECTIONS = 4;
    private boolean[][] visited;
    private int rows;
    private int cols;

    public int numIslands(char[][] grid) {
        //先得到行列号
        rows = grid.length;
        cols = grid[0].length;

        //初始话已访问数组，默认都是false
        visited = new boolean[rows][cols];

        int islandCount = 0;
        //循环二维数组
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //如果点是1，并且没有被访问过，开始DFS
                if (grid[i][j] == '1' && !visited[i][j]) {
                    dfs(i, j, grid);
                    islandCount++;
                }
            }
        }

        return islandCount;
    }

    private void dfs(int i, int j, char[][] grid) {
        //递归终止条件
        //处理逻辑中已经有判断，这里不需要
        /*if (grid[i][j] == '0' || visited[i][j]) {
            return;
        }*/

        //模板，先标记已访问
        visited[i][j] = true;

        //处理逻辑
        //向四个方向走一步，相当于遍历出边，判断值是否为1
        int ni;
        int nj;
        for (int k = 0; k < DIRECTIONS; k++) {
            ni = i + dx[k];
            nj = j + dy[k];
            //防止数组下标越界
            if (ni < 0 || nj < 0 || ni >= rows || nj >= cols) {
                continue;
            }
            //如果遇到1，且未被访问，继续DFS
            if (grid[ni][nj] == '1' && !visited[ni][nj]) {
                dfs(ni, nj, grid);
            }
        }
    }
}

//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3