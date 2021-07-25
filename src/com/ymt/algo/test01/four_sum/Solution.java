package com.ymt.algo.test01.four_sum;

import java.util.*;

/**
 * 思路:
 * 1. 先排序数组，然后索引逐步后移使用dfs求解
 * 注意: 第四个元素一直是重复元素时，不要重复处理
 * @author yumingtao
 * @date 2021/7/25 18:53
 */
public class Solution {
    List<List<Integer>> ans;
    List<Integer> sub;

    public List<List<Integer>> fourSum(int[] nums, int target) {
        ans = new ArrayList<>();
        sub = new ArrayList<>();

        //因为不考虑顺序，可以先对数组进行排序
        Arrays.sort(nums);
        dfs(nums, 0, 0, target);

        return ans;
    }

    private void dfs(int[] nums, int index, int sum, int target) {
        //终止条件
        if (sub.size() == 4) {
            if (target == sum) {
                ans.add(new ArrayList<>(sub));
            }
            return;
        }

        //访问所有可能的出边
        for (int i = index; i < nums.length; i++) {
            if (i != index && nums[i] == nums[i - 1]) {
                continue;
            }
            sum += nums[i];
            sub.add(nums[i]);

            dfs(nums, i + 1, sum, target);

            sub.remove(sub.size() - 1);
            sum -= nums[i];
        }
    }

    public static void main(String[] args) {
        //int[] nums = new int[]{1,3};
        //int[] nums = new int[]{2, 2, 2, 2, 2};
        int[] nums = new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 20, 20, 20, 20, 20, 20, 20, 20, 20,
                20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 40, 40,
                40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50,
                50, 50, 50, 50, 50, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 70, 70, 70, 70, 70, 70, 70, 70,
                70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 90,
                90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90};
        //int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        Solution solution = new Solution();
        List<List<Integer>> ans = solution.fourSum(nums, 200);
        System.out.println(ans);
    }
}
