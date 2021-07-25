package com.ymt.algo.w05.best_time_to_buy_and_sell_stock_ii;

/**
 * 思路：
 * 1.最完美的情况是获取prices[i]-prices[i-1]>0区间的收益
 *
 * @author yumingtao
 * @date 2021/7/25 12:48
 */
public class Solution2 {
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int val = prices[i] - prices[i - 1];
            if (val > 0) {
                profit += val;
            }
        }

        return profit;
    }
}
