package com.ymt.algo.w09.shortest_path_in_binary_matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 思路:
 * 1.BFS
 *
 * @author yumingtao
 * @date 2021/8/18 13:17
 */
public class Solution6 {
    /**
     * 八个方向的方向数组，从⬆️开始顺时针方向
     */
    private int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    private int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    int n;
    //注意初始步数是1
    int ans = 1;

    public int shortestPathBinaryMatrix(int[][] grid) {
        n = grid.length;
        //特例判断
        if (n == 1) {
            return grid[0][0] == 1 ? -1 : 1;
        }
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }

        boolean[][] visited = new boolean[n][n];

        //设置起始点[0, 0]初始值
        Point pStart = new Point(0, 0);
        Queue<Point> q = new LinkedList<>();
        q.add(pStart);
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int size = q.size();
            //注意要遍历完每一层
            for (int i = 0; i < size; i++) {
                Point p = q.poll();
                int x = p.x;
                int y = p.y;

                //终止条件
                if (x == n - 1 && y == n - 1) {
                    return ans;
                }

                //循环所有的出边，八个方向
                for (int k = 0; k < 8; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];

                    //判断边界
                    if (nx < 0 || ny < 0 || nx >= n || ny >= n || grid[nx][ny] == 1 || visited[nx][ny]) {
                        continue;
                    }

                    Point np = new Point(nx, ny);
                    q.add(np);
                    visited[nx][ny] = true;
                }
            }

            //当前层结束，累计步数+1
            ans++;
        }

        //始终没有相遇，返回-1
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

    public static void main(String[] args) {
        //int[][] grid = new int[][]{{1, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        //int[][] grid = new int[][]{{0, 0, 0, 0}, {1, 0, 0, 1}, {0, 1, 0, 0}, {0, 0, 0, 0}};
        //int[][] grid = {{0, 0, 0, 0, 1}, {1, 0, 0, 0, 0}, {0, 1, 0, 1, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}};
        //int[][] grid = new int[][]{{0, 0, 0}, {1, 0, 0}, {1, 1, 0}};
        int[][] grid = new int[][]{{0, 1}, {1, 0}};
        //int[][] grid = new int[][]{{0}};
        Solution6 solution5 = new Solution6();
        int result = solution5.shortestPathBinaryMatrix(grid);
        System.out.println(result);
    }
}
