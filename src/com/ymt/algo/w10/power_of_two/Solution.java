package com.ymt.algo.w10.power_of_two;

/***
 * @author yumingtao
 * @date 2021/8/22 19:40
 */
public class Solution {
    public boolean isPowerOfTwo(int n) {
        //一个数n，有32个bit
        //当只有一个bit是1的时候，才是2的幂次
        //可以使用lowbit,即n的lowbit等与n本身
        return n > 0 && n == (n & (-n));
    }
}
