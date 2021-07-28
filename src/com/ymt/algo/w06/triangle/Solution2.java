package com.ymt.algo.w06.triangle;

import java.util.List;

/**
 * 思路：
 * 1.使用DP，从最后一行开始递推
 * 2.可以得到递推的状态转移方程：dp[i][j] = min(dp[i+1][j], dp[i+1][j+1]) + triangle.get(i).get(j)
 *
 * @author yumingtao
 * @date 2021/7/28 10:12
 */
public class Solution2 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        //根据题可知，三角形高度和底边元素个数相同
        int[][] dp = new int[n + 1][n + 1];

        //从最后一层开始向上遍历
        for (int i = n - 1; i >= 1; i--) {
            //当前层
            List<Integer> cur = triangle.get(i);
            //遍历这一层中所有的元素
            for (int j = 0; j < cur.size(); j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + cur.get(j);
            }
        }

        return dp[0][0];
    }
}
