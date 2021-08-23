package com.ymt.algo.w10.powx_n;

/***
 * @author yumingtao
 * @date 2021/8/23 19:21
 */
public class Solution2 {
    public double myPow(double x, int n) {
        if (n == 0) return 1;

        //注意直接对int取负数会超出范围，所以先转乘long
        long N = n;
        if (n < 0) return 1 / pow(x, -N);
        return pow(x, N);
    }

    private double pow(double x, long n) {
        double ans = 1;
        while (n > 0) {
            if ((n & 1) == 1) ans *= x;
            n = n >> 1;
            x *= x;
        }

        return ans;
    }
}
