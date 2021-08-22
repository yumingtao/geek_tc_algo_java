package com.ymt.algo.w10.number_of_1_bits;

/**
 * @author yumingtao
 * @date 2021/8/22 19:02
 */
public class Solution2 {
    public int hammingWeight(int n) {
        int count = 0;
        while(n != 0){
            count++;
            //lowbit清零最低位的0
            n = n & (n - 1);
        }
        return count;
    }
}
