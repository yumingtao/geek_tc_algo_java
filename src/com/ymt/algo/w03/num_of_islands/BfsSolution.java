package com.ymt.algo.w03.num_of_islands;

import javafx.util.Pair;
import sun.java2d.pipe.SpanIterator;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 思路：
 * 1.找到一个值为1的点，标记为访问过
 * 2.从这个值1的点向4个方向走，如果遇到1继续往下走，同时标记为已访问过，遇到0的时候返回
 * 3.这样可以找到某一个值为1的点，可以到达的所有为1的点
 * 4.继续找下一个没有访问过的1开始访问
 * 5.可以DFS或BFS实现
 * 6.从主函数进入DFS或BFS的次数即为岛屿数量
 * <p>
 * 本例使用BFS
 *
 * @author yumingtao
 * @date 7/6/21 21:51 PM
 */
public class BfsSolution {
    private final int[] dx = {-1, 0, 0, 1};
    private final int[] dy = {0, 1, -1, 0};
    private final int DIRECTIONS = 4;
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
                //如果点是1，并且没有被访问过，开始BFS
                if (grid[i][j] == '1' && !visited[i][j]) {
                    bfs(i, j, grid);
                    islandCount++;
                }
            }
        }
        return islandCount;
    }

    private void bfs(int i, int j, char[][] grid) {
        //BFS中使用队列
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();

        //模板，加入值后，设置已访问过
        queue.add(new Pair(i, j));
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int ni;
            int nj;
            for (int k = 0; k < DIRECTIONS; k++) {
                ni = (int)pair.getKey() + dx[k];
                nj = (int)pair.getValue() + dy[k];
                //防止数组下标越界
                if (ni < 0 || nj < 0 || ni >= rows || nj >= cols) {
                    continue;
                }
                //如果遇到1，且未被访问
                if (grid[ni][nj] == '1' && !visited[ni][nj]) {
                    queue.add(new Pair(ni, nj));
                    visited[ni][nj] = true;
                }
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