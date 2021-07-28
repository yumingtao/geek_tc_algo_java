package com.ymt.algo.w06.triangle;

import java.util.List;

/**
 * 思路：
 * 1.使用DP，从顶向下递推
 * 2.可以得到递推的状态转移方程：dp[i][j] = min(dp[i-1][j-1], dp[i-1][j]) + triangle.get(i).get(j)
 *
 * @author yumingtao
 * @date 2021/7/28 10:12
 */
public class Solution3 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        //根据题可知，三角形高度和底边元素个数相同
        int[][] dp = new int[n][n];
        dp[0][0] = triangle.get(0).get(0);

        //从最后一层开始向上遍历
        for (int i = 1; i < n; i++) {
            //当前层
            //当前成元素的个数和i相同
            //List<Integer> cur = triangle.get(i);

            //遍历当前层层所有的j，j的取值范围[0, i-1]

            //考虑左侧边界j==0时，上一层只有j==0是合法
            dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);

            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
            }

            //考虑右侧边界j==i时，上一层只有i-2是合法
            dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
        }

        //遍历所有底层，找到最小的元素
        int min = dp[n - 1][0];
        for (int i = 0; i < n; i++) {
            min = Math.min(min, dp[n - 1][i]);
        }

        return min;
    }
}
