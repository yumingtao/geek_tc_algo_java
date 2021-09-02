package com.ymt.algo.w01.count_number_of_nice_subarrays;

/**
 * 参考官方题解：
 * odd存放奇数下标，两个奇树之间都是偶数
 * 在odd，[i-1, i]区间上存在m个偶数，[i+k-1, i+k]上存在n个偶数
 * 假设 i-l<l<=i, i+k-1<=r<i+k,在区间[l,r]存在i+k-1-i+1=k个奇数
 * 满足要求的[l,r]区间的个数，是[i-1, i]区间上存在m个偶数 * [i+k-1, i+k]上存在n个偶数
 * 即(odd[i]-odd[i-1]) * (odd[i+k] - odd[i+k-1])
 *
 * @author yumingtao
 * @date 2021/9/3 13:36
 */
public class Solution2 {
    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;

        //+2是因为要处理数组两个端点
        //保证统计数字没有，当nums中全是奇数时，设置完端点后的odd数组长度到达最大n+2
        int[] odd = new int[n + 2];

        //先挑出数组中奇数
        int l = 0;
        for (int i = 0; i < n; i++) {
            if ((nums[i] & 1) == 1) {
                //注意nums中第一个奇数有效下标从1开始
                odd[++l] = i;
            }
        }

        //统计完奇数下标之后，设置两端的值
        //将索引0处设置为-1，保证包含odd[0]这个奇数值
        odd[0] = -1;

        //将nums中最后一个奇数有效下标后的位置添加为n
        //保证在最后一个奇数有效下标后还有非奇数的数字个数都包含进来
        odd[++l] = n;

        int ans = 0;
        //循环奇数数组
        for (int i = 1; i <= l - k; i++) {
            ans += (odd[i] - odd[i - 1]) * (odd[i + k] - odd[i + k - 1]);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1};
        //int[] nums = {2,2,2,1,2,2,1,2,2,2};
        Solution2 s = new Solution2();
        int ans = s.numberOfSubarrays(nums, 3);
        System.out.println(ans);
    }
}
