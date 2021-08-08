package com.ymt.algo.w06.best_time_to_buy_and_sell_stock_with_cooldown;

import java.util.Arrays;

/**
 * 思路：按找从哪里来的思路考虑，即从入边着手，列表法表示的状态方程
 * ｜ 当前状态？   ｜ 前一天做什么？ ｜ 前一天条件？   ｜ 代价/收益？    ｜ 完成之后的状态  ｜
 * ｜ -----      ｜ -----       ｜ -----        ｜ -----        ｜ -----        ｜
 * ｜ f[i][j][l] ｜ 买入         ｜ j==0,l==0    ｜ -prices[i+1] ｜ f[i][1][0]   ｜
 * ｜            ｜ 卖出         ｜ j==1,l==0或1  ｜ +prices[i+1] ｜ f[i][0][1]   ｜
 * ｜            ｜ 什么都不做    ｜  无           ｜ 无           ｜ f[i][j][0]   ｜
 * @author yumingtao
 * @date 2021/8/7 16:42
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
        //有冷冻期，加上一个维度,冷冻期只有0和1两个取值
        int[][][] f = new int[n + 1][2][2];
        //初始化，将不合法或是未计算过的情况设置为一个极小值
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(f[i][0], -1000000000);
            Arrays.fill(f[i][1], -1000000000);
        }

        //第0天不持有任何股票且冷冻期是0的时候的收益是0
        //第0天持有1股股票的时候不论冷冻期是0还是1，收益都是不合法的
        //即f[0][1][0] = -1000000000或f[0][1][1]
        f[0][0][0] = 0;

        //遍历所有状态，从入边角度
        for (int i = 1; i <= n; i++) {
            //遍历所有策略，0-不持有股票，表示卖出，1-持有股票，表示买入
            for (int j = 0; j <= 1; j++) {
                //不用循环l这个维度
                //for (int l = 0; l <= 1; l++) {
                //第i天，可以是第i-1天基础上什么都没做
                //什么都不做，对于持有的股票，该持有继续持有，该不持有则继续不持有
                //什么都不做，针对冷冻期，不管第i天冷冻期是0还是1，经过了一天，冷冻期变成了0
                //所以在第i-1的基础上什么都不做，变成第i天的状态就是f[i][j][0]
                f[i][j][0] = Math.max(f[i - 1][j][0], f[i - 1][j][1]);

                //第i天完成后的状态，手上不持有股票
                //可以是前一天持有股票在第i天卖出，那么第i-1天持有股票，l可以是1也可以是0
                //也可以是前一天本来就不持有股票，什么都不做，那么经历了一天，冷冻期必然是0
                //在上一步已经计算过f[i][j][0]
                if (j == 0) {
                    f[i][j][1] = Math.max(f[i - 1][1][0], f[i - 1][1][1]) + adjustedPrices[i];
                }

                // 第i天完成后的状态，手上持有股票
                // 可以是前一天不持有股票在第i天买入，能够买入，第i-1天不持有股票，l必然是0
                // 也可以是本来前一天就持有，冷冻期可以是l==0，也可以是l==1, 那么经历了一天，冷冻期必然变成0
                // 在循环一开始已经计算过f[i][j][0]，两种情况取一个最大值
                if (j == 1) {
                    f[i][j][0] = Math.max(f[i][j][0], f[i - 1][0][0] - adjustedPrices[i]);
                }
                //}
            }
        }

        int ans = 0;
        for (int l = 0; l <= 1; l++) {
            ans = Math.max(ans, f[n][0][l]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{1, 2, 3, 0, 2};
        Solution2 solution = new Solution2();
        int result = solution.maxProfit(prices);
        System.out.println(result);
    }
}