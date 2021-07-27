package com.ymt.algo.w05.coin_change;

/**
 * 思路:
 * 1.不能直接用贪心
 * 2.直接使用dfs会超时，使用dfs+记忆化
 *
 * @author yumingtao
 * @date 2021/7/24 18:38
 */
public class Solution {
    private int[] visited;

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        //使用数组下标[1, amount]
        visited = new int[amount + 1];
        return dfs(coins, amount);
    }

    private int dfs(int[] coins, int amount) {
        //终止条件，<0说明面额超了
        if (amount < 0) {
            return -1;
        }

        //刚好找开，结束
        if (amount == 0) {
            return 0;
        }

        //如果之前处理过，直接返回
        if (visited[amount] != 0) {
            return visited[amount];
        }

        //处理逻辑
        //循环每条出边，求出每条出边返回的值中最小的
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int balance = amount - coins[i];
            int result = dfs(coins, balance);

            if (result >= 0) {
                min = Math.min(min, result + 1);
            }
        }

        //判断最后是否可以找开
        visited[amount] = min == Integer.MAX_VALUE ? -1 : min;
        return visited[amount];
    }

    public static void main(String[] args) {
        int[] coins = new int[]{1};
        Solution solution = new Solution();
        System.out.println(solution.coinChange(coins, 0));
    }
}
