package com.ymt.algo.w09.shortest_path_in_binary_matrix;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 思路:
 * 1.双向BFS
 * 2.注意双向bfs时
 * 3.使用已访问标记数组防止重复访问
 * 4.使用[i,j]生成Long形式hash函数
 *
 * @author yumingtao
 * @date 2021/8/18 13:17
 */
public class Solution4 {
    /**
     * 八个方向的方向数组，从⬆️开始顺时针方向
     */
    private int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    private int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    int n;

    public int shortestPathBinaryMatrix(int[][] grid) {
        n = grid.length;
        //特例判断
        if (n == 1) {
            return grid[0][0] == 1 ? -1 : 1;
        }
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }

        Map<Long, Integer> pathBegin = new HashMap<>();
        Map<Long, Integer> pathEnd = new HashMap<>();
        boolean[][] visitedBegin = new boolean[n][n];
        boolean[][] visitedEnd = new boolean[n][n];

        //初始化map
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pathBegin.put(getHashLong(i, j), (int) 1e9);
                pathEnd.put(getHashLong(i, j), (int) 1e9);
            }
        }

        //设置起始点[0, 0]初始值
        pathBegin.put(getHashLong(0, 0), 1);
        Point pStart = new Point(0, 0);
        Queue<Point> qBegin = new LinkedList<>();
        qBegin.add(pStart);
        visitedBegin[0][0] = true;

        //设置起始点[n-1, n-1]初始值
        pathEnd.put(getHashLong(n - 1, n - 1), 1);
        Point pEnd = new Point(n - 1, n - 1);
        Queue<Point> qEnd = new LinkedList<>();
        qEnd.add(pEnd);
        visitedEnd[n - 1][n - 1] = true;

        //两端各走一步进行处理
        while (!qBegin.isEmpty() || !qEnd.isEmpty()) {
            int result = checkWithBfs(grid, qBegin, pathBegin, pathEnd, visitedBegin);
            if (result != 0) {
                return result;
            }

            result = checkWithBfs(grid, qEnd, pathEnd, pathBegin, visitedEnd);
            if (result != 0) {
                return result;
            }
        }

        return -1;
    }

    private int checkWithBfs(int[][] grid, Queue<Point> q, Map<Long, Integer> path, Map<Long, Integer> pathOther,
                             boolean[][] visited) {
        if (!q.isEmpty()) {
            int size = q.size();
            //注意要遍历完每一层
            for(int i = 0; i < size; i++) {
                Point p = q.poll();
                int x = p.x;
                int y = p.y;
                Long pHash = getHashLong(x, y);
                int pPath = path.get(pHash);

                //循环所有的出边，八个方向
                for (int k = 0; k < 8; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];

                    //判断边界
                    if (nx < 0 || ny < 0 || nx >= n || ny >= n || grid[nx][ny] == 1 || visited[nx][ny]) {
                        continue;
                    }

                    Long npHash = getHashLong(nx, ny);
                    if (path.get(npHash) > pPath + 1) {
                        path.put(npHash, pPath + 1);
                        Point np = new Point(nx, ny);
                        q.add(np);
                        visited[nx][ny] = true;

                        //终止条件，在另一个map里，访问的步数不是1e9,说明相遇
                        if (pathOther.get(npHash) != (int) 1e9) {
                            //注意在之前已经加1，所以此处要-1
                            return path.get(npHash) + pathOther.get(npHash) - 1;
                        }
                    }
                }
            }
        }
        return 0;
    }

    private String getHashString(int i, int j) {
        return String.format("row:%d;col:%d", i, j);
    }

    private Long getHashLong(int i, int j) {
        return (i + 100) * 100 + j + 100l;
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
        //int[][] grid = new int[][]{{1, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        int[][] grid = new int[][]{{0, 0, 0, 0}, {1, 0, 0, 1}, {0, 1, 0, 0}, {0, 0, 0, 0}};
        //int[][] grid = new int[][]{{0, 0, 0}, {1, 0, 0}, {1, 1, 0}};
        //int[][] grid = new int[][]{{0, 1}, {1, 0}};
        //int[][] grid = new int[][]{{0}};
        Solution4 solution3 = new Solution4();
        int result = solution3.shortestPathBinaryMatrix(grid);
        System.out.println(result);
    }
}
