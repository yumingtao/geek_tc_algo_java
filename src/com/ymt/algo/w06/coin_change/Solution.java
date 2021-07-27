package com.ymt.algo.w06.coin_change;

/**
 * 思路:
 * 1.使用DP实现
 *
 * @author yumingtao
 * @date 2021/7/27 14:37
 */
public class Solution {
    public int coinChange(int[] coins, int amount) {
        //定义opt数组，使用下标从一开始，所以长度为amount+1
        int[] opt = new int[amount + 1];
        //当金额为0时不需要兑换
        opt[0] = 0;
        //先遍历所有的状态
        for (int i = 1; i <= amount; i++) {
            opt[i] = Integer.MAX_VALUE - 1;
            //再遍历决策
            for (int j = 0; j < coins.length; j++) {
                int tmpAmount = i - coins[j];
                if (tmpAmount >= 0) {
                    opt[i] = Math.min(opt[i], opt[tmpAmount] + 1);
                }
            }
        }

        return opt[amount] == Integer.MAX_VALUE - 1 ? -1 : opt[amount];
    }

    public static void main(String[] args) {
        int[] coins = new int[]{2};
        Solution solution = new Solution();
        int count = solution.coinChange(coins, 3);
        System.out.println(count);
    }
}
