package com.ymt.algo.w04.binary_search;

/**
 * 具有严格的单调性，使用普通二分查找模板
 *
 * @author yumingtao
 * @date 7/14/21 5:43 PM
 */
public class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] < target) {
                //target在右半段
                left = mid + 1;
            } else {
                //target在左半段
                right = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
        System.out.println(solution.search(nums, 9));
    }
}
