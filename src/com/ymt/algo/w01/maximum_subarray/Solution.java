package com.ymt.algo.w01.maximum_subarray;

/**
 * @author yumingtao
 * @date 2021/9/8 14:25
 */
public class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n + 1];

        //先求出前缀和
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                ans = Math.max(ans, sum[j] - sum[i - 1]);
            }
        }

        return ans;
    }
}
