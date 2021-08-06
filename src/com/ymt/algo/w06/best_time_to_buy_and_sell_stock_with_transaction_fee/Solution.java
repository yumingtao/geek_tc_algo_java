package com.ymt.algo.w06.best_time_to_buy_and_sell_stock_with_transaction_fee;

import java.util.Arrays;

/**
 * 思路：同ii，注意咋交易的时候减去fee
 *
 * @author yumingtao
 * @date 2021/8/6 12:29
 */
public class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;

        //调整价格下标从1开始
        int[] adjustedPrices = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            adjustedPrices[i] = prices[i - 1];
        }
        //可以不用写，默认就是0
        //adjustedPrices[0] = 0;

        //定义第i天持有0/1股股票的最大收益
        int[][] f = new int[n + 1][2];
        //初始化，将不合法或是未计算过的情况设置为一个极小值
        for (int i = 0; i < n + 1; i++) {
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
                f[i][j] = f[i - 1][j];

                //卖出的时候，会在[i-1][1]的基础上获得adjustedPrices[i]的收益
                if (j == 0) {
                    //因为在前边已经设置过什么也不做的情况，即f[i][0] = f[i - 1][0]
                    //如果没有设置，此处需要将f[i][0]替换为f[i-1][0]
                    //表示在维持现状和卖出股票之间选择一个收益最大的更新当前的状态
                    f[i][0] = Math.max(f[i][0], f[i - 1][1] + adjustedPrices[i] - fee);
                }

                //买入的时候，会在[i-1][0]的基础上减去adjustedPrices[i]，才是当前的收益
                if (j == 1) {
                    //因为在前边已经设置过什么也不做的情况，即f[i][1] = f[i - 1][1]
                    //如果没有设置，此处需要将f[i][1]替换为f[i-1][1]
                    //表示在维持现状和买入股票之间选择一个收益最大的更新当前的状态
                    f[i][1] = Math.max(f[i][1], f[i - 1][0] - adjustedPrices[i]);
                }
            }
        }

        return f[n][0];
    }

    public static void main(String[] args) {
        //int[] prices = new int[]{1, 3, 2, 8, 4, 9};
        int[] prices = new int[]{1, 3, 7, 5, 10, 3};
        Solution solution = new Solution();
        int result = solution.maxProfit(prices, 3);
        System.out.println(result);
    }
}
