package com.ymt.algo.w07.number_of_islands;

/**
 * 思路：使用并查集
 * 1. 遍历所有的点做为起始点，遍历四个方向的出边
 * 2. 因为水都是相连的，可以把所有为0的点放到一个特殊的fa，代表水的集合
 * 3. 如果相邻是1，则放入同一个集合
 * 4. 统计集合的数量即可，因为0都指向了水的集合
 *
 * @author yumingtao
 * @date 2021/8/2 10:23
 */
public class Solution {
    private final int[] dx = {-1, 0, 0, 1};
    private final int[] dy = {0, -1, 1, 0};
    private int m;
    private int n;
    private int[] fa;
    public int numIslands(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int len = m * n;
        //初始化并查集, 最后一个元素代表都是水
        fa = new int[len + 1];
        for(int i = 0; i < len; i++){
            fa[i] = i;
        }
        fa[len] = len;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '0'){
                    //合并到水的集合
                    int index = getIndex(i, j);
                    unionSet(index, len);

                    continue;
                }

                //如果是‘1’，遍历四个方向出边
                for(int k = 0; k < 4; k++){
                    int ni = i + dx[k];
                    int nj = j + dy[k];

                    //判断是否出界，如果出界则继续处理其它方向出边
                    if(ni < 0 || nj < 0 || ni >= m || nj >= n){
                        continue;
                    }

                    //如果没有出界，判断该点是‘1’ 还是 ‘0’
                    int nIndex = getIndex(ni, nj);
                    if(grid[ni][nj] == '1'){
                        int index = getIndex(i, j);
                        //合并[ni, nj]和[i, j]所在集合
                        unionSet(nIndex, index);
                    }else{
                        //合并[ni, nj]和水所在集合
                        unionSet(nIndex, len);
                    }
                }
            }
        }

        //统计结果，'0'点都指向了fa[len]，所以在[0, n)直接统计即可
        int ans = 0;

        for(int i = 0; i < len; i++){
            if(i == fa[i]){
                ans++;
            }
        }

        return ans;
    }

    /**
     * 计算[i, j]并查集的索引
     */
    private int getIndex(int i, int j){
        return i * n + j;
    }

    //查找集合代表，并压缩路径
    private int find(int x){
        if(x == fa[x]){
            return x;
        }

        fa[x] = find(fa[x]);
        return fa[x];
    }

    /**
     * 合并x和y所在的集合
     */
    private void unionSet(int x, int y){
        x = find(x);
        y = find(y);

        if(x != y){
            fa[x] = y;
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