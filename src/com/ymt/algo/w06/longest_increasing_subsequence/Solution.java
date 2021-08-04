package com.ymt.algo.w06.longest_increasing_subsequence;

/**
 * 思路：动态规划
 * 1.f[i]表示，下标i时，以nums[i]结尾的，最长递增子序列的长度
 * 2.最优子结构
 * - 当某一个数nums[i]，它的最长递增子序列长度为f[i]
 * - 对于i后边的数，只要大于nums[i]，就可以认为最长递增子序列长度可以时f[i]+1
 * - 具有推导关系
 * 3.在j属于[0，i]时，状态转换为
 * - 如果nums[j] < nums[i]，只要i前边数小于nums[i]，就有f[i] = max(f[i], f[j] + 1)
 *
 * @author yumingtao
 * @date 2021/8/4 20:24
 */
public class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;

        //f[i]表示，在前i个数，以nums[i]为结尾的，最长递增子序列的长度
        int[] f = new int[n];
        //初始化，默认初始值1代表最长递增子序列只有自己
        f[0] = 1;

        int ans = 1;
        for (int i = 1; i < n; i++) {
            //初始化，默认初始值1代表最长递增子序列只有自己，
            f[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }

            ans = Math.max(ans, f[i]);
        }

        return ans;
    }
}
