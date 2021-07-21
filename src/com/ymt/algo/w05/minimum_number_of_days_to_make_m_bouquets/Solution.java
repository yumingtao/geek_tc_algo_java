package com.ymt.algo.w05.minimum_number_of_days_to_make_m_bouquets;

import java.util.Arrays;

/**
 * 思路：
 * 1.解空间是数组的[最小值，最大值]
 * 2.二分查找解空间，判断解是否合法
 *
 * @author mingtao
 * @date 2021/7/21 10:07
 */
public class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        //判断不合法条件
        //花总的数量不满足制作所有花束的数量
        if (m * k > bloomDay.length) {
            return -1;
        }

        int left = Arrays.stream(bloomDay).min().getAsInt();
        int right = Arrays.stream(bloomDay).max().getAsInt();

        while (left < right) {
            int mid = (left + right) / 2;
            if (isValid(bloomDay, m, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    private boolean isValid(int[] bloomDay, int m, int k, int day) {
        //已经盛开的连续的花的个数
        int flowersCount = 0;
        int bouquets = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            //判断某一天已经盛开的连续的花的个数
            if (bloomDay[i] <= day) {
                flowersCount++;
                //当可以制作一束花的时候
                if (flowersCount >= k) {
                    bouquets++;
                    //剩余的可用的花的数量
                    flowersCount -= k;
                }
            } else {
                //不满足连续的条件，可用花的数量清零
                flowersCount = 0;
            }
        }
        return bouquets >= m;
    }

    public static void main(String[] args) {
        int[] bloomDay = new int[]{1000000000, 1000000000};
        Solution solution = new Solution();
        int minDay = solution.minDays(bloomDay, 1, 1);
        System.out.println(minDay);
    }
}