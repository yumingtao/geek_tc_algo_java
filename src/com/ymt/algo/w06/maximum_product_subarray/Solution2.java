package com.ymt.algo.w06.maximum_product_subarray;

/**
 * 思路：使用动态规划
 *
 * @author yumingtao
 * @date 2021/8/4 10:52
 */
public class Solution2 {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] fmax = new int[n];
        int[] fmin = new int[n];

        fmax[0] = fmin[0] = nums[0];

        int ans = fmax[0];
        //遍历所有的状态
        for (int i = 1; i < n; i++) {
            fmax[i] = Math.max(fmax[i - 1] * nums[i], Math.max(fmin[i - 1] * nums[i], nums[i]));
            fmin[i] = Math.min(fmax[i - 1] * nums[i], Math.min(fmin[i - 1] * nums[i], nums[i]));

            ans = Math.max(ans, fmax[i]);
        }

        return ans;
    }

    public static void main(String[] args) {
        //int[] nums = new int[]{2, 3, -2, 4};
        //int[] nums = new int[]{2, 0, -1};
        //int[] nums = new int[]{0, 2};
        //int[] nums = new int[]{0};
        int[] nums = new int[]{-2, 3, -4};
        Solution2 solution2 = new Solution2();
        int result = solution2.maxProduct(nums);
        System.out.println(result);
    }
}
