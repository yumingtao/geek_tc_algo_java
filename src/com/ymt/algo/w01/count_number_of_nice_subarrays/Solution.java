package com.ymt.algo.w01.count_number_of_nice_subarrays;

/**
 * @author yumingtao
 * @date 2021/9/2 10:33
 */
public class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;

        //前缀和数组
        int[] s = new int[n + 1];

        //下标变成从1开始，并且取模，变成都是有1和0组成的数组
        //那么问题变成了，求连续子数组，使子数组之和刚好等于k
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + nums[i - 1] % 2;
        }

        int ans = 0;
        //第l个数到第r数的区间段和=s[r] - s[l - 1]
        //如果s[r] - s[l - 1]==k，说明数组在[l,r]满足要求,ans++
        /*
        //经过测试，直接使用前缀和求解，会出现超时
		for(int r = 1; r <= n; r++){
			for(int l = 1; l <= r; l++){
				if(s[r] - s[l - 1] == k) ans++;
			}
		}*/

        /**进行优化
         *r在[1,n]和l在[1,r-1]上s[r] - s[l - 1]=k
         *可推导出，i在[1,n],j在[0,i]上有s[i]-s[j]=k -> s[j]=s[i]-k
         *即i在[1,n],j在[0,i]上，前缀和数组s有多少个s[j]=s[i]-k
         */

        //计数数组,因为前缀和数组s实在nums[i]取模之后计算的，所以count的最大长度不会超过s数组的长度
        int[] count = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            count[s[i]]++;
            //System.out.println("s[i]:" + s[i] + ", count:" + count[s[i]]);
        }

        for (int i = 1; i <= n; i++) {
            if (s[i] - k >= 0) {
                ans += count[s[i] - k];
            }
        }

        return ans;
    }
}
