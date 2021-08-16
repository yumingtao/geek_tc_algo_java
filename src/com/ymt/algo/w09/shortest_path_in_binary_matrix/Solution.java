package com.ymt.algo.w09.shortest_path_in_binary_matrix;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 思路：
 * 1. 从[0, 0]开始，向八个方向移动
 * 2. 最短路径使用bfs
 *
 * @author yumingtao
 * @date 2021/8/16 10:59
 */
public class Solution {
    /**
     * 定义八个方向数组,从⬆️开始顺时针
     */
    private int[] dx = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    private int[] dy = new int[]{0, 1, 1, 1, 0, -1, -1, -1};

    public int shortestPathBinaryMatrix(int[][] grid) {
        //初始化数据
        int n = grid.length;

        //特例判断
        if(grid[0][0] == 1 || grid[n - 1][n - 1] == 1){
            return -1;
        }

        boolean[][] visited = new boolean[n][n];
        Queue<Point> queue = new LinkedList<>();
        Map<Point, Integer> map = new HashMap<>();

        //从[0, 0]开始bfs
        Point start = new Point(0, 0);
        queue.add(start);
        visited[0][0] = true;
        map.put(start, map.getOrDefault(start, 0) + 1);

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int x = p.x;
            int y = p.y;

            //如果到达终点
            if (x == n - 1 && y == n - 1) {
                return map.get(p);
            }

            //循环8个方向出边
            for (int k = 0; k < 8; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                //判断出界情况
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                    continue;
                }

                //剪枝 - 判断是否访问过; 剪枝 - 如果是1，则走不通
                if (visited[nx][ny] || grid[nx][ny] == 1){
                    continue;
                }

                Point np = new Point(nx, ny);
                map.put(np, map.get(p) + 1);
                queue.add(np);
                visited[nx][ny] = true;
            }
        }

        return -1;
    }

    class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    //[[1,0,0],[1,1,0],[1,1,0]]
    //[[0,0,0],[1,1,0],[1,1,1]]
    //[[0,0,0],[1,0,0],[1,1,0]]
    public static void main(String[] args) {
        int[][] grid = new int[][]{{0, 0, 0}, {1, 0, 0}, {1, 1, 0}};
        Solution solution = new Solution();
        int result = solution.shortestPathBinaryMatrix(grid);
        System.out.println(result);
    }
}
/**
 *     0 1 2
 * --------
 * 0 ｜0 0 0
 * 1 ｜1 0 0
 * 2 ｜1 1 0
 *
 * (0,0) - (0,1),(1,1)
 * (0,1) - (1,1),(0,2),(1,2)
 * (1,1) - (0,2),(1,2),(2,2)
 * (0,2) - (1,2),(2,2)
 * ...
 */
