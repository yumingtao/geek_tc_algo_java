package com.ymt.algo.w10.reverse_bits;

/**
 * @author yumingtao
 * @date 2021/8/22 20:26
 */
public class Solution2 {
    public int reverseBits(int n) {
        int ans = 0;
        int index;
        //循环每一个位, 0是n的最低位
        for (int i = 0; i < 32; i++) {
            //ans向左移动以为，留出位置，利用0与任何数｜都是任何数本身的特性，设置ans留出来的位置的值是n的第i位的值
            ans = ans << 1 | (n >> i & 1);
        }

        return ans;
    }
}
