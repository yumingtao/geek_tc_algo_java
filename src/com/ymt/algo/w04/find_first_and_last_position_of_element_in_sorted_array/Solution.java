package com.ymt.algo.w04.find_first_and_last_position_of_element_in_sorted_array;

/**
 * 思路:
 * 1.使用二分查找
 * 2.先找到小于target的数中最大的数的位置，得到的位置+1即为target开始的位置
 * 3.再找到大于target的数中最小的数的位置，得到的位置-1即为target结束的位置
 * 4.注意如果找不到，start>end
 *
 * @author mingtao
 * @date 2021/7/18 19:51
 */
public class Solution {
    public int[] searchRange(int[] nums, int target) {
        int left = -1;
        int right = nums.length - 1;
        int start;
        int end;

        //寻找小于target的数中最大的位置
        while(left < right){
            int mid = left + (right - left + 1) / 2;
            if(nums[mid] < target){
                left = mid;
            }else{
                right = mid - 1;
            }
        }

        //+1为target的位置
        start = right + 1;

        //寻找大于target的最小的位置
        left = 0;
        right = nums.length;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(nums[mid] > target){
                right = mid;
            }else{
                left = mid + 1;
            }
        }

        //right-1为target的结束位置
        end = right - 1;

        if(start > end){
            return new int[]{-1, -1};
        }

        return new int[]{start, end};
    }
}
