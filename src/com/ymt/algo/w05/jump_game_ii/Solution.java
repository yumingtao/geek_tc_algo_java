package com.ymt.algo.w05.jump_game_ii;

/**
 * 思路：
 * 1.在当前位置，找下一跳位置能够到达最长的进行
 * 2.因为下一跳位置选择能够到达最长的，已经包含了下一跳选择其它位置所能到达的最远点
 *
 * 注意：下一跳的最远距离应该是当前索引+当前元素值，即i+nums[i]
 *
 * @author mingtaoyu
 * @date 2021/7/25 17:08
 */
public class Solution {
    public int jump(int[] nums) {
        int ans = 0;
        int i = 0;
        while(i < nums.length - 1){
            //当前索引对应的值，表示可以往后走多少步
            int currentMax = nums[i];
            //判断当前最大步数是否可以到达结尾
            if(currentMax + i >= nums.length - 1){
                ans++;
                break;
            }

            int nextMaxIndex = 0;
            int nextMax = 0;
            //往后可以走i+1，到i+currentMax步
            //判断这些步中，判断哪一步的值+当前能够走的步数能够到达最远的索引
            for(int j = 1; j <= currentMax; j++){
                int nextIndex = i + j;
                if(nums[nextIndex] + nextIndex > nextMax){
                    nextMax = nums[nextIndex] + nextIndex;
                    nextMaxIndex = nextIndex;
                }
            }

            ans++;
            //直接跳到这一步
            i = nextMaxIndex;
        }

        return ans;
    }
}
