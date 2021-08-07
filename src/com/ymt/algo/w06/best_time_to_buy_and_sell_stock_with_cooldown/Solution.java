package com.ymt.algo.w06.best_time_to_buy_and_sell_stock_with_cooldown;

import java.util.Arrays;

/**
 * 思路：列表法表示的状态方程
 * ｜ 当前状态？｜ 下一天做什么？ ｜ 条件？ ｜ 代价/收益？ ｜ 完成之后的状态 ｜
 * ｜ ----- ｜ ----- ｜ ----- ｜ ----- ｜ ----- ｜
 * ｜ f[i][j][l] ｜ 买入 ｜ j==0,l==0 ｜ -prices[i+1] ｜ f[i+1][1][0] ｜
 * ｜            ｜ 卖出 ｜ j==1,l==0 ｜ +prices[i+1] ｜ f[i+1][0][1] ｜
 * ｜            ｜ 什么都不做｜  无    ｜ 无           ｜ f[i+1][j][0] ｜
 *
 *
 * @author yumingtao
 * @date 2021/8/7 16:42
 */
public class Solution {
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

        //第0天不持有任何股票时候的收益是0
        //第0天持有1股股票的时候收益是不合法，即f[0][1][0] = -1000000000
        //第0天有冷冻期，也是不合法的
        f[0][0][0] = 0;

        //遍历所有状态，从出边角度
        for (int i = 0; i < n; i++) {
            //遍历所有策略，0-不持有股票，表示卖出，1-持有股票，表示买入
            for (int j = 0; j <= 1; j++) {
                for (int l = 0; l <= 1; l++) {
                    if (j == 0 && l == 0) {
                        f[i + 1][1][0] = Math.max(f[i + 1][1][0], f[i][j][l] - adjustedPrices[i + 1]);
                    }

                    if (j == 1 && l == 0) {
                        f[i + 1][0][1] = Math.max(f[i + 1][0][1], f[i][j][l] + adjustedPrices[i + 1]);
                    }

                    //什么都不做，该持有继续持有，不持有则继续观望,
                    //什么都不做，经过了一天，即使有冷冻期也变成了0
                    f[i + 1][j][0] = Math.max(f[i + 1][j][0], f[i][j][l]);
                }
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
        Solution solution = new Solution();
        int result = solution.maxProfit(prices);
        System.out.println(result);
    }
}
