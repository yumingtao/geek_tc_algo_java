package com.ymt.algo.w04.sqrtx;

/**
 * 思路：
 * 1.一个非负整数的平方根应该在[1,x)之间
 * 2.使用二分法，取[1,x)中的一个数k，找满足k^2<=x中最大的k
 *
 * @author yumingtao
 * @date 2021/7/16 23:12
 */
public class Solution {
    public int mySqrt(int x) {
        long left = 1;
        long right = x;

        while (left < right) {
            long mid = (left + right + 1) / 2;
            if (mid * mid <= x) {
                //说明结果可能在右半段
                left = mid;
            } else {
                //说明结果可能在左半段
                right = mid - 1;
            }
        }

        return (int) right;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.mySqrt(2147395599));
    }
}
