package com.ymt.algo.w06.maximum_product_subarray;

import java.util.Arrays;

/**
 * 思路：双指针
 *
 * @author yumingtao
 * @date 2021/8/4 09:18
 */
public class Solution1 {
    public int maxProduct(int[] nums) {
        int n = nums.length;

        //先找到数组中的最大值，表示一个元素的连续子数组的最大值
        int result = Arrays.stream(nums).max().getAsInt();

        for (int i = 0; i < n; i++) {
            int temp = nums[i];
            for (int j = i + 1; j < n; j++) {
                temp = temp * nums[j];
                result = Math.max(result, temp);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        //int[] nums = new int[]{2, 3, -2, 4};
        //int[] nums = new int[]{2, 0, -1};
        //int[] nums = new int[]{0, 2};
        int[] nums = new int[]{0};
        //int[] nums = new int[]{-2, 3, -4};
        Solution1 solution1 = new Solution1();
        int result = solution1.maxProduct(nums);
        System.out.println(result);
    }
}
