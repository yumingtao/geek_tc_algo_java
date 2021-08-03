package com.ymt.algo.w06.jump_game_ii;

/**
 * 思路: 使用动态规划
 * 1. 最优子结构：跳到终点前的每个点都是最少跳跃数过来的
 * 2. 从i点出发，能够跳跃的步数是决策，状态方程如下
 * - j表示能从i跳跃到的点
 * - 当第一次到达j点时，dp[j] = dp[i] + 1
 * - 如果不是第一次到达j点，dp[j] = min(dp[i]+1, dp[j])
 *
 * @author yumingtao
 * @date 2021/8/3 09:11
 */
public class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        //遍历所有状态
        for (int i = 0; i < n; i++) {
            //如果位置是0，不能跳跃，直接过
            if (nums[i] == 0) {
                continue;
            }

            //遍历所有策略，注意j要在边界之内
            for (int j = i + 1; j <= i + nums[i] && j < n; j++) {
                if (dp[j] == 0) {
                    //如果第一次跳到该点，记录跳跃数为跳到i的步数+1
                    dp[j] = dp[i] + 1;
                } else {
                    //如果不是第一次跳到该点，跳到i的步数+1和当前记录跳跃步数的最小值
                    dp[j] = Math.min(dp[i] + 1, dp[j]);
                }
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0};
        Solution solution = new Solution();
        int result = solution.jump(nums);
        System.out.println(result);
    }
}

//5,9,3,2,1,0,2,3,3,1,0,0

//             0 1 2 3 4
//输入: nums = [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。

/**
 * dp[1] = dp[0] + 1 = 1
 * dp[2] = dp[0] + 1 = 1
 * <p>
 * dp[2] = dp[1] + 1 = 2 or 1 取最小的1
 * dp[3] = dp[1] + 1 = 2
 * dp[4] = dp[1] + 1 = 2
 * <p>
 * dp[3] = dp[2] + 1 = 2 or 3 取最小的2
 * <p>
 * dp[4] = dp[3] + 1 = 2 or 3 or 4 取最小的2
 */

//输入: nums = [2,3,0,1,4]
//输出: 2