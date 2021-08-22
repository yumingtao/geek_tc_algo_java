package com.ymt.algo.w10.reverse_bits;

/**
 * @author yumingtao
 * @date 2021/8/22 20:22
 */
public class Solution {
    public int reverseBits(int n) {
        int ans = 0;
        int index;
        //循环每一个位, 0是n的最低位
        for (int i = 0; i < 32; i++) {
            //颠倒之后的位置
            index = 31 - i;
            //获取n在二进制下的第i位，通过(n >> i) & 1取出第i位的值
            if (((n >> i) & 1) == 1) {
                //设置ans在二进制下的第index位为1, 利用1与任何数｜都是其本身
                ans = (ans | (1 << index));
            } else {
                //设置ans在二进制下的第index位为0，利用0与任何数&都是0
                ans = (ans & (~(1 << index)));
            }
        }

        return ans;
    }
}
