package com.ymt.algo.w03.redundant_connection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yumingtao
 * @date 7/4/21 8:45 PM
 */
public class Solution {
    private List<List<Integer>> outEdges = new ArrayList<>();
    /**
     * 定义节点是否被访问过的数组
     */
    private boolean[] visited;
    private boolean hasCycle;

    public int[] findRedundantConnection(int[][] edges) {
        //先求出二维数组中出现的最大的数，即为图最大节点的值
        int maxVal = getMaxValue(edges);
        visited = new boolean[maxVal + 1];
        initVisited(maxVal);
        //初始化出边数组
        for (int i = 0; i <= maxVal; i++) {
            outEdges.add(new ArrayList<>());
        }

        //给定的输入是一个二维数组
        //图的存储有三种方式：邻接矩阵，邻接表，出边数组
        //通常采用邻接数组存储，比较简单，空间复杂度是O(n+m)，n为点数，m为边数
        //无向图可以看成是x->y和y->x，x和y互为出边，需要添加两次出边
        for (int[] edge : edges) {
            addOutEdge(edge[0], edge[1]);
            addOutEdge(edge[1], edge[0]);

            //每加一条边就判断是否多了环
            //如果有环，那么加的这条边就是题解
            //每次加边时需要还原已访问节点数组
            initVisited(maxVal);

            //无向图，判断是否有环，使用dfs，递归
            //需要判断节点是否被访问过，如果被访问过，说明有环
            //节点不重复1...n
            dfs(edge[0], -1);
            if (hasCycle) {
                return edge;
            }
        }
        return null;
    }

    private int getMaxValue(int[][] edges){
        int n = 0;
        for (int[] edge : edges) {
            n = Math.max(n, Math.max(edge[0], edge[1]));
        }

        return n;
    }

    private void initVisited(int n){
        for (int i = 0; i <= n; i++) {
            visited[i] = false;
        }
    }

    private void dfs(int x, int father) {
        //dfs遍历图的模板
        //先标记节点已经被访问过
        visited[x] = true;

        //遍历点x的所有的出边
        for (int y : outEdges.get(x)) {
            //如果访问过，说明存在环
            //因为是按照x->y和y->x，x和y互为出边添加的出边数组，y可能又找回到x，需要排除这种情况
            if (y == father) {
                continue;
            }

            if (visited[y]) {
                hasCycle = true;
                return;
            } else {
                dfs(y, x);
            }
        }
    }

    /**
     * 构造出边数组的模板
     */
    private void addOutEdge(int x, int y) {
        outEdges.get(x).add(y);
    }
}
