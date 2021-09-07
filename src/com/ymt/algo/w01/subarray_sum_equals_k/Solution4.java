package com.ymt.algo.w01.subarray_sum_equals_k;

/**
 * @author yumingtao
 * @date 2021/9/6 22:21
 */
public class Solution4 {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j >= 0; j--) {
                sum += nums[j];
                if(sum == k){
                    ans++;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        //int[] nums = {-1, -1, 1};
        //int[] nums = {1, 1, 1};
        int[] nums = {1, -1, 0};
        Solution4 solution = new Solution4();
        int result = solution.subarraySum(nums, 0);

        System.out.println(result);
    }
}
