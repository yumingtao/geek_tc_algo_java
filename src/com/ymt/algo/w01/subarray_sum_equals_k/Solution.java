package com.ymt.algo.w01.subarray_sum_equals_k;

/**
 * @author yumingtao
 * @date 2021/9/6 22:21
 */
public class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }

        int ans = 0;
        //一定要注意前缀和数组下标从1开始
        //因为定义了长度是n+1，所以循环时，一定要注意范围是[1,n]，是包含n的
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                if (sum[j] - sum[i - 1] == k) {
                    ans++;
                }
            }
        }

        return ans;
    }
}
