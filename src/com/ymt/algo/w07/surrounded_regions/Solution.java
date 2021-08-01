package com.ymt.algo.w07.surrounded_regions;

/**
 * 思路：使用并查集
 * 1.定义一个的并查集，长度是吗m*n+1，其中最后一个元素作为所有边界元素的fa
 * 2.遍历所有的点，找到边界上为'O'的点即和它相邻的'O'点，让他们于边界元素合并
 * 3.遍历所有的点，找到为'O'且于边界不是同一个集合的点，并修改为'X'
 *
 *
 * @author yumingtao
 * @date 2021/8/1 18:04
 */
public class Solution {
    final int[] dx = {-1, 0, 0, 1};
    final int[] dy = {0, 1, -1, 0};
    int[] fa;
    int m;
    int n;
    public void solve(char[][] board) {
        m = board.length;
        n = board[0].length;

        //定义一个的并查集，长度是吗m*n+1
        //其中最后一个元素作为所有边界元素的fa
        int len = m * n;
        fa = new int[len + 1];
        //初始化并查集
        for(int i = 0; i < m; i++){
            for(int j = 0; j< n; j++){
                //计算[i,j]对应的坐标
                int index = getIndex(i, j);
                fa[index] = index;
            }
        }
        //设置一个边界元素的fa
        fa[len] = len;

        //遍历所有的元素，以每个元素作为出发点
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                //如果不是'O'，继续遍历
                if(board[i][j] == 'X'){
                    continue;
                }

                //如果是'O', 也就是找到了一个'O'做为起始点
                //开始遍历四个方向出边，来找到和这个起始'O'相邻的所有'O'点
                for(int k = 0; k < 4; k++){
                    int ni = i + dx[k];
                    int nj = j + dy[k];
                    //如果这个起始'O'的是在边界上，把它和边界元素合并
                    if(ni < 0 || nj < 0 || ni >= m || nj >= n ){
                        int index = getIndex(i, j);
                        //fa[find(index)] = find(len);
                        unionSet(index, len);
                        //注意这里不能break;
                        //因为跟这个'O'相邻的所有'O'都要指向边界元素的fa
                    }else{
                        //如果在k这个方向是，判断起始'O'不是在边界上
                        //判断它的出边是不是'O'
                        if(board[ni][nj] == 'O'){
                            //如果是'O'，先将它和[i, j]合并
                            //注意，如果这个ni，nj在边界上，会在后边的遍历中将其和边界元素合并
                            //fa[find(getIndex(ni, nj))] = find(getIndex(i, j));
                            int nIndex = getIndex(ni, nj);
                            int index = getIndex(i, j);
                            unionSet(nIndex, index);
                        }
                    }
                }
            }
        }

        //遍历所有的点，如果是'O'且没有指向边界fa，就更新成'X'
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'O' && find(getIndex(i, j)) != fa[len]){
                    board[i][j] = 'X';
                }
            }
        }

    }

    //[0, 0]的索引是0，然后每行索引按照列号自然序列递增
    //当前行的最后一个元素的索引和下一行的第一个元素索引连续
    private int getIndex(int i, int j){
        return  i * n + j;
    }

    private int find(int x){
        if(x == fa[x]){
            return x;
        }

        fa[x] = find(fa[x]);
        return fa[x];
    }

    //合并x和y所在的集合
    private void unionSet(int x, int y){
        x = find(x);
        y = find(y);

        if(x != y){
            fa[x] = y;
        }
    }
}
