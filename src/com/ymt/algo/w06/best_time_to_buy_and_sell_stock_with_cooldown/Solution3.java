package com.ymt.algo.w06.best_time_to_buy_and_sell_stock_with_cooldown;

/**
 * @author yumingtao
 * @date 2021/8/8 22:25
 */
public class Solution3 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        /**
         * f[i][0]:手上持有股票的状态，有两个来源
         * 1.前一天本来就持有，但是什么都没做
         * 2.前一天买入的，冷冻期为0
         *
         * f[i][1]:手上不持有股票，冷冻期为0
         * 1.前一天本来就不持有股票，冷冻期为1或0
         *
         *
         * f[i][2]:手上不持有股票，冷冻期为1
         * 1.前一天卖出，冷冻期为1
         */
        int[][] f = new int[n][3];

        //初始化f
        f[0][0] = -prices[0];

        for (int i = 1; i < n; i++) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] - prices[i]);
            f[i][1] = Math.max(f[i - 1][1], f[i - 1][2]);
            f[i][2] = f[i - 1][0] + prices[i];
        }

        return Math.max(f[n - 1][1], f[n - 1][2]);
    }

    public static void main(String[] args) {
        int[] prices = new int[]{1, 2, 3, 0, 2};
        Solution3 solution = new Solution3();
        int result = solution.maxProfit(prices);
        System.out.println(result);
    }
}
