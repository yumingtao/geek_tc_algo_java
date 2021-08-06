package com.ymt.algo.w06.best_time_to_buy_and_sell_stock_ii;

import java.util.Arrays;

/**
 * 思路: 使用动态规划 + 滚动数组优化
 * 1. f[i][j]表示第i天，手上持有j股股票的收益，可以有三种策略
 * - 什么都不做，f[i][j] = f[i-1][j]
 * - 卖出，f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + adjustedPrices[i]);
 * - 买入，f[i][1] = Math.max(f[i - 1][1], f[i - 1][0] - adjustedPrices[i]);
 * 2.注意：该题中是f[i - 1][0] -adjustedPrices[i]，因为可以多次买卖，需要用之前收益减去adjustedPrices[i]
 * 3.注意使用滚动数组，只要在所有的f的第一维&1即可
 *
 * @author yumingtao
 * @date 2021/8/6 09:20
 */
public class Solution2 {
    public int maxProfit(int[] prices) {
        int n = prices.length;

        //调整价格下标从1开始
        int[] adjustedPrices = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            adjustedPrices[i] = prices[i - 1];
        }
        //可以不用写，默认就是0
        //adjustedPrices[0] = 0;

        //定义第i天持有0/1股股票的最大收益
        //注意定义3*2维的数组节省空间
        int[][] f = new int[3][2];
        //初始化，将不合法或是未计算过的情况设置为一个极小值
        for (int i = 0; i <= 2; i++) {
            Arrays.fill(f[i], -1000000000);
        }

        //第0天不持有任何股票时候的收益是0
        //第0天持有1股股票的时候收益是不合法，即f[0][1] = -1000000000
        f[0][0] = 0;

        //遍历所有状态
        for (int i = 1; i < n + 1; i++) {
            //遍历所有策略，0-不持有股票，表示卖出，1-持有股票，表示买入
            for (int j = 0; j <= 1; j++) {
                //什么都不做，该持有继续持有，不持有则继续观望
                f[i & 1][j] = f[(i - 1) & 1][j];

                //卖出的时候，会在[i-1][1]的基础上获得adjustedPrices[i]的收益
                if (j == 0) {
                    //因为在前边已经设置过什么也不做的情况，即f[i][0] = f[i - 1][0]
                    //如果没有设置，此处需要将f[i][0]替换为f[i-1][0]
                    //表示在维持现状和卖出股票之间选择一个收益最大的更新当前的状态
                    f[i & 1][0] = Math.max(f[i & 1][0], f[(i - 1) & 1][1] + adjustedPrices[i]);
                }

                //买入的时候，会在[i-1][0]的基础上减去adjustedPrices[i]，才是当前的收益
                if (j == 1) {
                    //因为在前边已经设置过什么也不做的情况，即f[i][1] = f[i - 1][1]
                    //如果没有设置，此处需要将f[i][1]替换为f[i-1][1]
                    //表示在维持现状和买入股票之间选择一个收益最大的更新当前的状态
                    f[i & 1][1] = Math.max(f[i & 1][1], f[(i - 1) & 1][0] - adjustedPrices[i]);
                }
            }
        }

        return f[n & 1][0];
    }

    public static void main(String[] args) {
        //int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        int[] prices = new int[]{7, 6, 4, 3, 1};
        //int[] prices = new int[]{1, 2};
        Solution2 solution2 = new Solution2();
        int result = solution2.maxProfit(prices);
        System.out.println(result);
    }
}
