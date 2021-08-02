package com.ymt.algo.w06.perfect_squares;

/**
 * 思路:
 * 1.参考兑换零钱，只不过零钱随着状态的增加也会增加
 *
 * @author yumingtao
 * @date 2021/8/2 20:47
 */
public class Solution {
    public int numSquares(int n) {;
        int[] f = new int[n + 1];

        //先遍历所有的状态
        for(int i = 1; i <= n; i++){
            f[i] = Integer.MAX_VALUE;
            //再遍历所有的决策
            for(int j = 1; j * j <= i; j++){
                f[i] = Math.min(f[i], f[i - j * j] + 1);
            }
        }

        return f[n];
    }
}
