package com.ymt.algo.w06.best_time_to_buy_and_sell_stock_iii;

import java.util.Arrays;

/**
 * 思路：动态规划
 * 1. 增加一个限制条件就增加一个维度
 *
 * @author yumingtao
 * @date 2021/8/6 10:46
 */
public class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        //交易次数
        int transactionCount = 2;

        //调整价格下标从1开始
        int[] adjustedPrices = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            adjustedPrices[i] = prices[i - 1];
        }
        //可以不用写，默认就是0
        //adjustedPrices[0] = 0;

        //定义第i天持有0/1股股票的最大收益，注意增加一维做为交易次数
        int[][][] f = new int[n + 1][2][transactionCount + 1];
        //初始化，将不合法或是未计算过的情况设置为一个极小值
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(f[i][0], -1000000000);
            Arrays.fill(f[i][1], -1000000000);
        }

        //第0天不持有任何股票也没有交易次数的时候的收益是0
        //第0天持有1股股票的时候收益是不合法，即f[0][1][0] = -1000000000
        f[0][0][0] = 0;

        //遍历所有状态
        for (int i = 1; i < n + 1; i++) {
            //遍历所有状态，0-不持有股票，表示卖出，1-持有股票，表示买入
            for (int j = 0; j <= 1; j++) {
                //遍历交易次数，有三种不同的策略
                for (int k = 0; k <= transactionCount; k++) {
                    //什么都不做，该持有继续持有，不持有则继续观望，交易次数也是维持原状
                    f[i][j][k] = f[i - 1][j][k];

                    //卖出的时候，会在[i-1][1][k]的基础上获得adjustedPrices[i]的收益
                    //注意这个k-1放在哪里都行，一次买和卖算一次交易
                    if (j == 0 && k > 0) {
                        //因为在前边已经设置过什么也不做的情况，即f[i][0][k] = f[i - 1][0][k]
                        //如果没有设置，此处需要将f[i][0][k]替换为f[i-1][0][k]
                        //表示在维持现状和卖出股票之间选择一个收益最大的更新当前的状态
                        f[i][0][k] = Math.max(f[i][0][k], f[i - 1][1][k - 1] + adjustedPrices[i]);
                    }

                    //买入的时候，会在[i-1][0]的基础上减去adjustedPrices[i]，才是当前的收益
                    if (j == 1) {
                        //因为在前边已经设置过什么也不做的情况，即f[i][1][k] = f[i - 1][1][k]
                        //如果没有设置，此处需要将f[i][1][k]替换为f[i-1][1][;]
                        //表示在维持现状和买入股票之间选择一个收益最大的更新当前的状态
                        f[i][1][k] = Math.max(f[i][1][k], f[i - 1][0][k] - adjustedPrices[i]);
                    }
                }
            }
        }

        //遍历交易次数，获取最大值
        int ans = 0;
        for (int k = 0; k <= transactionCount; k++) {
            ans = Math.max(ans, f[n][0][k]);
        }

        return ans;
    }
}
