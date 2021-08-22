package com.ymt.algo.w10.number_of_1_bits;

/**
 * @author yumingtao
 * @date 2021/8/21 22:35
 */
public class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            //向左移动i个位置，使第i个位置在最低位
            //根据按位与的特性，任何数x & 1 == 1
            //即如果1和x按位与还等于1本身，说明x==1
            if (((n >> i) & 1) == 1) count++;
        }
        return count;
    }
}
