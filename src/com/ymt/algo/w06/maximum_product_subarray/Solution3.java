package com.ymt.algo.w06.maximum_product_subarray;

/**
 * @author yumingtao
 * @date 2021/8/26 19:38
 */
public class Solution3 {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int fMax = nums[0];
        int fMin = nums[0];
        int ans = fMax;
        int temp;
        for (int i = 1; i < n; i++) {
            temp = Math.max(fMax * nums[i], Math.max(fMin * nums[i], nums[i]));
            fMin = Math.min(fMax * nums[i], Math.min(fMin * nums[i], nums[i]));
            fMax = temp;

            ans = Math.max(ans, fMax);
        }

        return ans;
    }
}
