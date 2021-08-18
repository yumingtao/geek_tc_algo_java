package com.ymt.algo.w09.shortest_path_in_binary_matrix;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 思路：
 * 1.普通bfs
 * 2.根据[i, j]，计算string hash
 * 3.性能较差800ms左右
 *
 * @author yumingtao
 * @date 2021/8/16 10:59
 */
public class Solution2 {
    /**
     * 八个方向的方向数组，从⬆️开始顺时针方向
     */
    private int[] dx = {-1,-1,0,1,1,1,0,-1};
    private int[] dy = {0,1,1,1,0,-1,-1,-1};

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        //特例判断
        if(grid[0][0] == 1 || grid[n - 1][n - 1] == 1){
            return -1;
        }

        boolean[][] visited = new boolean[n][n];
        Map<String, Integer> path = new HashMap<>();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                path.put(getHashString(i, j), (int)1e9);
            }
        }

        //设置起始点初始值
        path.put(getHashString(0, 0), 1);

        Queue<Point> q = new LinkedList<>();
        Point p0 = new Point(0, 0);
        q.add(p0);
        visited[0][0] = true;

        while(!q.isEmpty()){
            Point p = q.poll();
            int x = p.x;
            int y = p.y;

            //终止条件
            if(x == n - 1 && y == n - 1){
                return path.get(getHashString(x, y));
            }

            //循环所有的出边，八个方向
            for(int k = 0; k < 8; k++){
                int nx = x + dx[k];
                int ny = y + dy[k];

                //判断边界
                if(nx < 0 || ny < 0 || nx >= n || ny >= n){
                    continue;
                }

                //判断是否访问过或是1
                if(grid[nx][ny] == 1 || visited[nx][ny] == true){
                    continue;
                }

                Point np = new Point(nx, ny);
                int pPath = path.get(getHashString(x, y));
                String hash = getHashString(nx, ny);

                if(path.containsKey(hash)){
                    if(path.get(hash) > pPath + 1){
                        path.put(hash, pPath + 1);
                        q.add(np);
                        visited[nx][ny] = true;
                    }
                }

            }
        }

        return -1;

    }

    private String getHashString(int i, int j){
        return String.format("row:%d;col:%d", i, j);
    }


    class Point{
        public int x;
        public int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

    }

    //[[1,0,0],[1,1,0],[1,1,0]]
    //[[0,0,0],[1,1,0],[1,1,1]]
    //[[0,0,0],[1,0,0],[1,1,0]]
    public static void main(String[] args) {
        int[][] grid = new int[][]{{0, 0, 0}, {1, 0, 0}, {1, 1, 0}};
        Solution2 solution = new Solution2();
        int result = solution.shortestPathBinaryMatrix(grid);
        System.out.println(result);
    }
}
