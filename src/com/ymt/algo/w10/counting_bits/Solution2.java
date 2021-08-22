package com.ymt.algo.w10.counting_bits;

/**
 * @author yumingtao
 * @date 2021/8/22 21:39
 */
public class Solution2 {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];

        //使用DP
        ans[0] = 0;
        for (int i = 1; i <= n; i++) {
            // 之前的统计相当于，用一个数，不停地减去lowbit，每减去一个，1的个数就+1
            // 反过来可以理解成，当前数字1的个数，是减去一个lowbit得到的数字中1的个数+1
            // num = num & (num - 1);
            //1010110  减去一次最低位的1得到下边的数
            //1010100
            ans[i] = ans[i & (i - 1)] + 1;
        }

        return ans;
    }
}
