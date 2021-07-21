package com.ymt.algo.w05.split_array_largest_sum;

/**
 * 思路：
 * 1.分割m个数组，数组的元素和最大值范围，即解空间是[单个元素的最大值，数组元素之和]
 * 2.使用二分查找解空间，判断解是否合法，在合法的解中找最小值
 * 3.关键是判断解合法的算法：贪心分割数组，在数组元素和满足<=解的时候，判断能够分割的数组的个数需<=m
 *
 * @author yumingtao
 * @date 2021/7/20 16:43
 */
public class Solution {
    public int splitArray(int[] nums, int m) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < nums.length; i++) {
            //每个分一组中求最大的值做为left，注意不能直接从0开始
            left = Math.max(left, nums[i]);
            //全部放在一起为一组，所以要求sum
            right += nums[i];
        }

        while (left < right) {
            int mid = (left + right) / 2;
            /**
             * 求和<=target的子数组的个数
             * 如果满足条件的子数组的个数<=m，说明给定的target是合法的
             */
            if (isValid(nums, mid, m)) {
                //找合法的值中最小的值，可能在左半段
                right = mid;
            } else {
                //否则可能在有半段
                left = mid + 1;
            }
        }

        return right;
    }

    private boolean isValid(int[] nums, int target, int m) {
        int sum = 0;
        //子数组的个数至少是1，所以从1开始
        int groupCount = 1;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum <= target) {
                //继续放入，看sum的情况
                continue;
            } else {
                //超了，sum从nums[i]开始
                sum = nums[i];
                //子数组数+1
                groupCount++;
            }
        }

        //注意此处<m的含义
        //小于m组都可以分出满足条件的，那继续分成m个当然肯定是满足条件的
        return groupCount <= m;
    }

    private int calculateSum(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 4, 4};
        Solution solution = new Solution();
        int minVal = solution.splitArray(nums, 3);
        System.out.println(minVal);
    }
}