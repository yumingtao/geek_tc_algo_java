package com.ymt.algo.w06.triangle;

import java.util.List;

/**
 * 思路：
 * 1.可以得到递推的状态转移方程：path[i-1][j] = min(path[i][j], path[i][j+1]) + triangle.get(i).get(j)
 *
 * @author yumingtao
 * @date 2021/7/28 10:12
 */
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        //从最糊一层开始向上遍历
        for (int i = triangle.size() - 1; i >= 1; i--) {
            //当前层
            List<Integer> cur = triangle.get(i);

            //上一层
            List<Integer> pre = triangle.get(i - 1);

            //遍历这一层中所有的元素
            for (int j = 0; j < cur.size() - 1; j++) {
                //相邻两个元素最小值 + 上一层元素的值，做为上一层的最小值
                int val = Math.min(cur.get(j), cur.get(j + 1)) + pre.get(j);
                //边界
                if (i >= 1) {
                    triangle.get(i - 1).set(j, val);
                }
            }
        }

        return triangle.get(0).get(0);
    }
}
