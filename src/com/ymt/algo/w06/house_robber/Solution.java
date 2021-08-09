package com.ymt.algo.w06.house_robber;

/**
 * @author yumingtao
 * @date 2021/8/9 20:09
 */
public class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];

        //第一个房间不偷
        dp[0][0] = 0;
        //第一个房间偷
        dp[0][1] = nums[0];

        //循环所有的状态
        for (int i = 1; i < n; i++) {
            //如果当前房间不偷，当前的金额为前一个房间偷或者不偷中最大一个
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);

            //如果当前房间偷, 肯定是上一个房间不偷+当前房间的金额
            dp[i][1] = dp[i - 1][0] + nums[i];
        }

        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}
