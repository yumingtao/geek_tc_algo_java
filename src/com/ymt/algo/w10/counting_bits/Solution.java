package com.ymt.algo.w10.counting_bits;

/***
 * @author yumingtao
 * @date 2021/8/22 21:05
 */
public class Solution {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            ans[i] = getBitCounts(i);
        }

        return ans;
    }

    //利用lowbit
    private int getBitCounts(int num) {
        int count = 0;

        while (num != 0) {
            count++;
            num = num & (num - 1);
        }

        return count;
    }
}
