package com.ymt.algo.w06.climbing_stairs;

/**
 * @author yumingtao
 * @date 2021/7/28 01:35
 */
public class Solution {
    public int climbStairs(int n) {
        int[] f = new int[n + 1];
        //已经在楼顶了，站在那里不动，算一种走法
        f[0] = 1;
        //只差一阶到楼顶，只有一种走法
        f[1] = 1;
        for(int i = 2; i <= n; i++){
            f[i] = f[i-1] + f[i-2];
        }

        return f[n];
    }
}
