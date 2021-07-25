package com.ymt.algo.w05.best_time_to_buy_and_sell_stock_ii;

/**
 * 思路：
 * 1.如果当前没持有股票，看后一天的股票价格
 * - 如果后一天是涨，则买
 * - 如果后一天是跌，则不买
 * 2. 当前持有股票，不能继续买入，只能卖了之后再买
 * - 如果后一天涨，则不卖
 * - 如果后一天跌，则卖
 *
 * @author mingtao
 * @date 2021/7/25 12:46
 */
public class Solution1 {
    public int maxProfit(int[] prices) {
        //收益
        int profit = 0;

        //是否持有股票
        boolean hold = false;

        for (int i = 0; i < prices.length; i++) {
            if (!hold) {
                //如果当前没持有股票，看后一天的股票价格
                //如果后一天是涨，则买
                //如果后一天是跌，则不买
                if (i == prices.length - 1) {
                    //已经是最后一天
                    break;
                }
                if (prices[i + 1] > prices[i]) {
                    //买入股票
                    profit -= prices[i];
                    hold = true;
                }
            } else {
                //当前持有股票，不能继续买入，只能卖了之后再买
                //如果后一天涨，则不卖
                //如果后一天跌，则卖
                if (i == prices.length - 1 || prices[i + 1] < prices[i]) {
                    profit += prices[i];
                    hold = false;
                }
            }
        }

        return profit;
    }
}
