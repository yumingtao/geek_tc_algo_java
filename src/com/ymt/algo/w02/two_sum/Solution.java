package com.ymt.algo.w02.two_sum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yumingtao
 * @date 6/24/21 10:37 PM
 */
public class Solution {
    public int[] twoSum(int[] nums, int target) {

        //key:nums[i], value:i
        //维护i-1之前值和下标的映射
        //当i在往后遍历的时候，需要用到数组前边的值和下标
        Map<Integer, Integer> map = new HashMap<>();

        int temp;
        for (int i = 0; i < nums.length; i++) {
            temp = target - nums[i];
            //判断b是否出现及在数组中的位置
            if (map.containsKey(temp)) {
                return new int[]{map.get(temp), i};
            }
            map.put(nums[i], i);
        }

        //根据题，上边循环肯定返回值，这里只是为了方法编译能通过
        return new int[]{-1, -1};
    }
}
