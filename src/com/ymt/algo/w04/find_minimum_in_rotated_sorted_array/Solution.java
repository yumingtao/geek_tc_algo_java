package com.ymt.algo.w04.find_minimum_in_rotated_sorted_array;

/**
 * 思路:
 * 1. 可知，数组最后一个元素可以看成是target
 * 2. 查找当前数组中第一个小于等target的数，属于lower_bound问题
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
        int[] nums = new int[]{11, 13, 15, 17};
        Solution solution = new Solution();
        System.out.println(solution.findMin(nums));
    }
}

//输入：nums = [4,5,6,7,0,1,2]
//输出：0
//解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。

//输入：nums = [11,13,15,17]
//输出：11
//解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。