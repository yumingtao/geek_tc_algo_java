package com.ymt.algo.w01.count_number_of_nice_subarrays;

/**
 * 参考题解：使用滑动窗口
 * 1.先找到k个奇数
 * 2.然后再找第一个奇数后边的偶数的个数m，注意+1(没有奇数的情况)
 * 3.再找最后一个奇数后边有多少个偶数n，注意+1(没有奇数的情况)
 * 4.m*n
 *
 * @author yumingtao
 * @date 2021/9/3 13:36
 */
public class Solution3 {
    public int numberOfSubarrays(int[] nums, int k) {
        int len = nums.length;
        int ans = 0;
        int l = 0;
        int r = 0;
        int oddSum = 0;
        while (r < len) {
            //找到k个奇数
            //当找到之后，r走到了最后一个奇数后一个位置
            while (r < len && oddSum < k) {
                if ((nums[r] & 1) == 1) {
                    oddSum++;
                }
                r++;
            }

            //注意此处要判断oddSum是否合法
            if (oddSum == 0 || oddSum < k) {
                break;
            }

            //找到第一个奇数左边有多少个偶数
            //当找到是，l刚好走到第一个奇数的位置
            int m = 0;
            while (l < len && (nums[l] & 1) != 1) {
                m++;
                l++;
            }

            //找到最后一个奇数右边边有多少个偶数
            //当统计完成之后，r走到下一个奇数的位置
            int n = 0;
            while (r < len && (nums[r] & 1) != 1) {
                n++;
                r++;
            }

            ans += (m + 1) * (n + 1);

            oddSum--;
            //注意l之前是走到第一个奇数的位置，下一次循环前要向前走一步
            l++;
        }

        return ans;
    }

    public static void main(String[] args) {
        //int[] nums = {1, 1, 1, 1};
        //int[] nums = {2,2,2,1,2,2,1,2,2,2};
        //int[] nums = {1};
        //int[] nums = {2, 4, 6};
        int[] nums = {91473, 45388, 24720, 35841, 29648, 77363, 86290, 58032, 53752, 87188, 34428, 85343, 19801, 73201};
        Solution3 s = new Solution3();
        int ans = s.numberOfSubarrays(nums, 4);
        System.out.println(ans);
    }
}
