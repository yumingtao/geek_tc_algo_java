package com.ymt.algo.w01.subarray_sum_equals_k;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yumingtao
 * @date 2021/9/7 09:42
 */
public class Solution2 {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        Map<Integer, Integer> map = new HashMap<>();

        //注意，前缀和数组下标是从1开始，sum[0]=0,所以在map中将sum[0]的个数设置为1
        //初始化sum[0]
        map.put(sum[0], 1);
        int ans = 0;
        //注意，如果移动了数组下标从1开始，那么循环的时候也要从1开始，范围是[1, n]
        for (int i = 1; i <= n; i++) {
            //假设[i, j], i<=j, 根据区段和公式sum[j]-sum[i-1]=k,即sum[i-1]=sum[j]-k
            //sum[j],以j结尾的数组前缀和,因为i<=j,所以求j之前有多少个sum的值是sum[i]-k
            //使用map做为计数统计，因为包含负数，计数数组索引不能是负数，所以使用map

            //将数组下标移动到从1开始
            //前缀和数组可以进一步优化，这里只用到了一个sum[i - 1]
            sum[i] = sum[i - 1] + nums[i - 1];
            int key = sum[i] - k;
            if (map.containsKey(key)) {
                ans += map.get(key);
            }
            map.put(sum[i], map.getOrDefault(sum[i], 0) + 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        //int[] nums = {-1, -1, 1};
        int[] nums = {1, 1, 1};
        Solution2 solution = new Solution2();
        int result = solution.subarraySum(nums, 2);

        System.out.println(result);
    }
}
