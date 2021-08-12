package com.ymt.algo.w08.find_the_city_with_the_smallest_number_of_neighbors_at_a_threshold_distance;

/**
 * 思路: 使用Floyd算法
 *
 * @author yumingtao
 * @date 2021/8/12 13:34
 */
public class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        //先用邻接矩阵构造图
        int[][] d = new int[n][n];

        //假设所有点之间都彼此之间不连通，先初始化没有出边的两个点，设置为1e9
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                //x,y之间不可达，设置距离为正无穷
                d[x][y] = (int) 1e9;
                d[y][x] = (int) 1e9;
            }
        }

        //根据边数组，设置彼此连通的点之间的实际距离
        int x;
        int y;
        int z;
        for (int[] edge : edges) {
            x = edge[0];
            y = edge[1];
            z = edge[2];

            d[x][y] = z;
            d[y][x] = z;
        }

        //最后设置自身到自身的距离为0
        for (int i = 0; i < n; i++) {
            d[i][i] = 0;
        }

        //Floyd算法模板
        //1.先循环阶段
        for (int k = 0; k < n; k++) {
            //2.循环所有状态
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    //3.状态转换
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }

        //处理结果
        int ans = 0;
        int ansCount = n;
        int count;

        for (int i = 0; i < n; i++) {
            count = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && d[i][j] <= distanceThreshold) {
                    count++;
                }
            }

            if (count <= ansCount) {
                ansCount = count;
                ans = i;
            }
        }

        return ans;
    }
}
