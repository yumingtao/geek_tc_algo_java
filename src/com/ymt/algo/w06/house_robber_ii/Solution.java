package com.ymt.algo.w06.house_robber_ii;

/**
 * @author yumingtao
 * @date 2021/8/9 21:20
 */
public class Solution {
    public int rob(int[] nums) {
        int n = nums.length;

        //第一个要是偷了，最后一个就不能偷
        //第一个不偷，最后一个可以偷也可以不偷

        int[][][] dp = new int[n][2][2];
        //第一个不偷
        dp[0][0][0] = 0;
        //第一个偷了
        dp[0][1][1] = nums[0];

        for(int i = 1; i < n; i++){
            for(int k = 0; k <= 1; k++){
                //第一个没偷
                if(k == 0){
                    //当前也不偷
                    dp[i][0][0] = Math.max(dp[i - 1][0][0], dp[i - 1][1][0]);
                    //当前偷
                    dp[i][1][0] = dp[i - 1][0][0] + nums[i];
                }

                //第一个偷了
                if(k == 1){
                    //当前也不偷
                    dp[i][0][1] = Math.max(dp[i - 1][0][1], dp[i - 1][1][1]);

                    //当前偷
                    if(i < n - 1){
                        //如果不是最后一个，正常偷
                        dp[i][1][1] = dp[i - 1][0][1] + nums[i];
                    }else{
                        //最后一个不偷
                        dp[i][1][1] = dp[i - 1][0][1];
                    }
                }
            }
        }

        int ans = 0;

        for(int k = 0; k <= 1; k++){
            ans = Math.max(ans, Math.max(dp[n - 1][0][k], dp[n - 1][1][k]));
        }

        return ans;
    }
}
