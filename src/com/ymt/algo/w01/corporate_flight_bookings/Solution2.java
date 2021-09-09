package com.ymt.algo.w01.corporate_flight_bookings;

/**
 * 使用差分
 *
 * @author yumingtao
 * @date 2021/9/9 20:58
 */
public class Solution2 {
    public int[] corpFlightBookings(int[][] bookings, int n) {

        //使用差分，更新值的时候，l位置+d， r+1位置-d
        //0~n+1,所以长度是n+2
        int[] delta = new int[n + 2];
        for (int i = 0; i < bookings.length; i++) {
            int left = bookings[i][0];
            int right = bookings[i][1];
            int value = bookings[i][2];
            delta[left] += value;
            delta[right + 1] -= value;
        }

        //对差分数组delta求前缀和就得到计算后的原数组
        //delta下标是从0开始的，但是有效的值是从1开始的
        //前缀和数组ans从1开始
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + delta[i];
        }

        //答案要求返回的数组有效结果下标从0开始，所以需要整体向左移动数组,
        int[] ans = new int[n];
        for (int i = 1; i <= n; i++) {
            ans[i - 1] = sum[i];
        }

        return ans;
    }
}
