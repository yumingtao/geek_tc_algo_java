package com.ymt.algo.w07.number_of_provinces;

/**
 * 思路：
 * 1.使用并查集，通过将直接相连的城市合并并查集，最终会将直接相连或间接相连的城市都合并到一个集合
 * 2.最后统计集合代表的数量
 *
 * @author yumingtao
 * @date 2021/8/1 15:52
 */
public class Solution {
    int[] fa;
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        //一共有n个城市，建立n个城市的并查集，并初始化
        fa = new int[n];
        for(int i = 0; i < n; i++){
            fa[i] = i;
        }

        //遍历个点
        for(int i = 0; i < n; i++){
            //j从i+1开始，自己和自己不需要判断
            for(int j = i + 1; j < n; j++){
                //将两个直接相连的城市合并并查集
                if(isConnected[i][j] == 1){
                    unionSet(i, j);
                }
            }
        }

        //统计集合代表的数量，就是省份的数量
        int ans = 0;
        for(int i = 0; i < n; i++){
            //集合代表的fa指向它自己
            if(i == fa[i]){
                ans++;
            }
        }

        return ans;
    }

    private int find(int x){
        //找到集合代表，即根节点，它存储的值就是它自己
        if(x == fa[x]){
            return x;
        }

        return fa[x] = find(fa[x]);
    }

    //合并元素x和元素y的并查集
    private void unionSet(int x, int y){
        x = find(x);
        y = find(y);

        if(x != y){
            fa[x] = y;
        }
    }
}
