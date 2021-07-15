package com.ymt.algo.w04.find_minimum_in_rotated_sorted_array_ii;

import java.util.Map;

/**
 * 思路:
 * 1. 可知，数组最后一个元素可以看成是target，但是在在左侧也可能有target，去要排除掉
 * 2. 查找当前数组中第一个小于等于target的数，属于lower_bound问题
 * 3. 使用模版1.1
 *
 * @author yumingtao
 * @date 7/15/21 12:00 PM
 */
public class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length;
        int target = nums[nums.length - 1];

        //排除掉左侧有和target重复数据的情况
        while (nums[left] == target) {
            left++;
            //循环到数组尾部，说明数组中元素都一样，直接返回
            if (left == right) {
                return nums[right - 1];
            }
        }

        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] <= target) {
                //答案可能在左半段
                right = mid;
            } else {
                //不用包含left
                left = mid + 1;
            }
        }

        return nums[right];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 2, 2, 2};
        Solution solution = new Solution();
        System.out.println(solution.findMin(nums));
    }
}