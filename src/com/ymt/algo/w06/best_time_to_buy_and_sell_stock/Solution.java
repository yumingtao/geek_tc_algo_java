package com.ymt.algo.w06.best_time_to_buy_and_sell_stock;

import java.util.Arrays;

/**
 * 思路: 使用动态规划
 * 1. f[i][j]表示第i天，手上持有j股股票的收益，可以有三种策略
 * - 什么都不做，f[i][j] = f[i-1][j]
 * - 卖出，f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + adjustedPrices[i]);
 * - 买入，f[i][1] = Math.max(f[i - 1][1], 0 - adjustedPrices[i]);
 * 2.注意：该题中不是f[i - 1][0] -adjustedPrices[i]，因为只有一次买卖，当手上不持有股票是收益是0，
 * 所以直接-adjustedPrices[i]
 *
 * @author yumingtao
 * @date 2021/8/5 10:57
 */
public class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;

        //将prices调整成下边从1开始，prices[0]默认是0，没有意义
        int[] adjustedPrices = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            adjustedPrices[i] = prices[i - 1];
        }

        //DP数组
        int[][] f = new int[n + 1][2];
        //对数组初始化，不合法和没算过的状态设置为Integer.MIN_VALUE
        for (int i = 0; i <= n; i++) {
            Arrays.fill(f[i], -1000000000);
        }

        //第0天手上什么都没有，设置为0，f[0][1]是不合法状态
        //f[i][0]表示第i天手上不持有股票的收益，f[i][1]表示第i天手上持有股票的收益
        f[0][0] = 0;

        //遍历所有的状态
        for (int i = 1; i < n + 1; i++) {
            //遍历所有的策略
            //f[i][0]从两个状态过来，取两个状态中的最大值
            //1.手上本来就不持有股票，此时继续不持有，即f[i - 1][0]
            //2.手上持有股票，卖出，此时会获得当前价格的利润
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + adjustedPrices[i]);

            //f[i][1]从两个状态过来，取两个状态中的最大值
            //1.手上本来就持有股票，此时继续持有，即f[i - 1][1]
            //2.手上不持有股票，买入，此时会减去当前价格
            //注意：该题中不是f[i - 1][0] -adjustedPrices[i]，因为只有一次买卖，当手上不持有股票是收益是0，所以直接-adjustedPrices[i]
            f[i][1] = Math.max(f[i - 1][1], 0 - adjustedPrices[i]);
        }

        return f[n][0];
    }

    public static void main(String[] args) {
        //int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        //int[] prices = new int[]{7, 6, 4, 3, 1};
        int[] prices = new int[]{1, 2};
        Solution solution = new Solution();
        int result = solution.maxProfit(prices);
        System.out.println(result);
    }
}