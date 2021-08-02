package com.ymt.algo.w07.redundant_connection;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 思路:使用并查集
 * 1.如果是环，edge[0]和edge[1]指向相同的fa
 * 2.如果没有指向相同fa，合并edge[0]和edge[1]
 *
 * @author yumingtao
 * @date 2021/8/2 13:28
 */
public class Solution {
    private int[] fa;

    public int[] findRedundantConnection(int[][] edges) {
        int maxValue = getMaxValue(edges);

        //fa[0]没有用
        fa = new int[maxValue + 1];
        for (int i = 0; i <= maxValue; i++) {
            fa[i] = i;
        }

        //遍历edges，
        for (int[] edge : edges) {
            //如果edge[0]和edge[1]的fa相同，说明是环
            if (find(edge[0]) == find(edge[1])) {
                return edge;
            }
            //合并edge[0]和edge[1]所在集合
            unionSet(edge[0], edge[1]);
        }

        return null;
    }

    /**
     * 查找集合代表
     */
    private int find(int x) {
        if (x == fa[x]) {
            return x;
        }

        fa[x] = find(fa[x]);
        return find(fa[x]);
    }

    /**
     * 合并并查集
     */
    private void unionSet(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            fa[x] = y;
        }
    }

    private int getMaxValue(int[][] edges) {
        int max = 0;
        for (int[] edge : edges) {
            max = Math.max(max, Math.max(edge[0], edge[1]));
        }

        return max;
    }

    public static void main(String[] args) {
        //int[][] edges = new int[][]{{1, 2}, {1, 3}, {2, 3}};
        int[][] edges = new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        Solution solution = new Solution();
        solution.findRedundantConnection(edges);

        int[] result = solution.findRedundantConnection(edges);
        System.out.println(Arrays.stream(result).boxed().collect(Collectors.toList()));
    }
}

//输入: edges = [[1,2], [1,3], [2,3]]
//输出: [2,3]

//输入: edges = [[1,2], [2,3], [3,4], [1,4], [1,5]]
//输出: [1,4]
