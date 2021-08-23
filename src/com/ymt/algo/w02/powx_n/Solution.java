package com.ymt.algo.w02.powx_n;

/**
 * @author yumingtao
 * @date 2021/8/23 19:35
 */
public class Solution {
    public double myPow(double x, int n) {
        long N = n;
        if (n < 0) return 1 / pow(x, -N);
        return pow(x, N);
    }

    private double pow(double x, long n) {
        if (n == 0) return 1;
        double ans = pow(x, n / 2);

        if (n % 2 == 0) {
            ans *= ans;
        } else {
            ans = ans * ans * x;
        }
        return ans;
    }
}
