package com.ymt.algo.w10.powx_n;

/**
 * @author yumingtao
 * @date 2021/8/23 19:08
 */
public class Solution {
    public double myPow(double x, int n) {
        if (n == 0) return 1;

        //注意直接对int取负数会超出范围，所以先转乘long
        long N = n;
        if (n < 0) return 1 / pow(x, -N);
        return pow(x, N);
    }

    private double pow(double x, long n) {
        double ans = 1;

        for (int i = 0; i < 32; i++) {
            if ((n >> i & 1) == 1) ans *= x;
            x *= x;
        }

        return ans;
    }
}
