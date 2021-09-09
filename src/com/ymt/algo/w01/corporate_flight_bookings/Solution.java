package com.ymt.algo.w01.corporate_flight_bookings;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 暴力解法
 *
 * @author yumingtao
 * @date 2021/9/8 14:56
 */
public class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int m = bookings.length;
        int[] ans = new int[n];

        for (int i = 0; i < m; i++) {
            int start = bookings[i][0];
            int end = bookings[i][1];

            for (int j = start; j <= end; j++) {
                //j-1目的是下标从0开始
                ans[j - 1] += bookings[i][2];
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] bookings = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        //int[][] bookings = {{1, 2, 10}, {2, 2, 15}};
        Solution solution = new Solution();
        int[] ans = solution.corpFlightBookings(bookings, 5);
        System.out.println(Arrays.stream(ans).boxed().collect(Collectors.toList()));
    }
}
