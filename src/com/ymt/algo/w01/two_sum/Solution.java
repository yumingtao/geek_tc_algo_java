package com.ymt.algo.w01.two_sum;

import java.util.Arrays;

/**
 * 思路：
 * 使用双指针
 *
 * @author yumingtao.yu
 * @date 7/9/21 5:53 PM
 */
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{j, i};
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        //int[] input = new int[]{2, 7, 11, 15};
        int[] input = new int[]{3, 2, 4};
        Solution solution = new Solution();
        int[] result = solution.twoSum(input, 6);
        System.out.println(Arrays.toString(result));
    }
}

//输入：nums = [2,7,11,15], target = 9
//输出：[0,1]
//解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。