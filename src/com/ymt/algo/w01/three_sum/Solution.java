package com.ymt.algo.w01.three_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 思路：
 * 1.套用两数之和，nums[j] + nums[k] = -nums[i]
 *
 * @author yumingtao
 * @date 2021/7/19 22:25
 */
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        //先对数组进行排序
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            //求nums[j] + nums[k] = -nums[i]
            //相同的数字不能重复使用
            if(i > 0 && nums[i] == nums[i -1]){
                continue;
            }

            //i+1保证从j和k时大于i的
            List<List<Integer>> results = twoSum(nums, i + 1, 0 -  nums[i]);
            for(List<Integer> result : results){
                ans.add(result);
            }
        }

        return ans;
    }

    private List<List<Integer>> twoSum(int[] nums, int start, int target) {
        List<List<Integer>> ans = new ArrayList();
        int j = nums.length - 1;
        for (int i = start; i < j; i++) {
            //相同的数字不能重复使用
            if(i > start && nums[i] == nums[i - 1]){
                continue;
            }
            while (i < j && nums[i] + nums[j] > target) {
                j--;
            }
            if (i < j && nums[i] + nums[j] == target) {
                int[] result = new int[]{0 - target, nums[i], nums[j]};
                ans.add(Arrays.stream(result).boxed().collect(Collectors.toList()));
            }
        }

        return ans;
    }
}
