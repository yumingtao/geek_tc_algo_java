package com.ymt.algo.w06.maximum_subarray;

/**
 * 思路：使用动态规划
 *
 * @author yumingtao
 * @date 2021/8/4 15:03
 */
public class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];

        f[0] = nums[0];

        int ans = f[0];
        for (int i = 1; i < n; i++) {
            f[i] = Math.max(f[i - 1] + nums[i], nums[i]);
            ans = Math.max(ans, f[i]);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        Solution solution = new Solution();
        int result = solution.maxSubArray(nums);
        System.out.println(result);
    }
}

//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为6 。
