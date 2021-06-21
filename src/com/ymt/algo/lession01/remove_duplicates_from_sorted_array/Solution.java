package com.ymt.algo.lession01.remove_duplicates_from_sorted_array;

/**
 * Description
 *
 * @author yumingtao
 * @date 6/18/21 3:21 PM
 */
public class Solution {
    public int removeDuplicates(int[] nums) {
        //考虑什么条件下保留->后一个不等于前一个
        //相当于慢指针
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            //如果后一个不等于前一个，就保留
            //i-1有越界，考虑i==0的情况
            if (i == 0 || (nums[i] != nums[i - 1])) {
                nums[len++] = nums[i];
            }
        }
        return len;
    }

    public int removeDuplicates2(int[] nums) {
        //卫语句，考虑i==0的情况，i-1有越界
        if(nums.length == 0){
            return 0;
        }

        //相当于慢指针
        int len = 0;
        for (int i = 1; i < nums.length; i++) {
            //如果后一个不等于前一个，就保留
            if (nums[i] != nums[i - 1]) {
                nums[len++] = nums[i];
            }
        }
        return len;
    }
}
