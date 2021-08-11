package com.ymt.algo.w08.network_delay_time;

/***
 * 思路：使用BellMan-Ford算法
 *
 * @author yumingtao
 * @date 2021/8/11 17:12
 */
public class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        //定义n个点分别到起始点k的距离
        int[] dist = new int[n + 1];
        //初始化数组的值, 注意数组的下标从1开始
        for(int i = 1; i <= n; i++){
            dist[i] = (int)1e9;
        }

        //默认自己到自己距离是0
        dist[k] = 0;

        int x;
        int y;
        int z;
        boolean isUpdated;

        //遍历每一个点, 最多n-1轮，注意下标从1开始
        for(int j = 1; j < n; j++){
            isUpdated = false;
            //循环每条边，相等于分别从每条边找最短路径，最终找到整个图的最短路径
            for(int i = 0; i < times.length; i++){
                x = times[i][0];
                y = times[i][1];
                z = times[i][2];

                if(dist[x] + z < dist[y]){
                    dist[y] = dist[x] + z;
                    isUpdated = true;
                }
            }
            //如果没有更新了就结束
            if(!isUpdated){
                break;
            }
        }

        int ans = 0;
        //注意下标从1开始
        for(int i = 1; i <= n; i++){
            ans = Math.max(ans, dist[i]);
        }

        return ans == (int)1e9 ? -1 : ans;
    }
}
