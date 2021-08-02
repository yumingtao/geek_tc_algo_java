package com.ymt.algo.w06.jump_game;

/**
 * 思路:
 * 1.遍历每一个点是状态
 * 2.每一个点的值能够跳跃到的点是决策
 *
 * @author yumingtao
 * @date 2021/8/2 22:36
 */
public class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        //考虑极端情况
        if(n == 1) {
            return true;
        }else if(nums[0] == 0){
            return false;
        }

        //从第0个元素开始跳
        boolean[] f = new boolean[n];
        //注意要对第一个元素赋值
        f[0] = true;

        //保存j可以开始的索引位置，避免重复判断
        int startIndex = 0;
        //先遍历状态
        for(int i = 0; i < n; i++){
            //如果该点不能往后跳，尝试下一个点
            if(!f[i]){
                continue;
            }

            //如果nums[i]是0并且不是最后一个下标，设置为false，尝试下一个点
            if(nums[i] == 0 && i != n - 1){
                f[i] = false;
                continue;
            }

            if(startIndex == 0){
                startIndex = i + 1;
            }
            //遍历决策，跳跃的步数范围1...nums[i]
            for(int j = startIndex; j <= i + nums[i]; j++){
                //能够跳跃的步数
                //int jumpSteps = i + j;

                //如果可以跳跃的步数超出了数组范围
                if(j >= n - 1){
                    return true;
                }else if(nums[j] != 0){
                    f[j] = true;
                }
            }

            startIndex = i + nums[i] + 1;
        }

        /*for(int i = 0; i < n; i++){
            System.out.println(f[i]);
        }*/

        return f[n - 1];
    }
}
