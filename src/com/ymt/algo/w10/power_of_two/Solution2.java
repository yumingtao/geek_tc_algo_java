package com.ymt.algo.w10.power_of_two;

/**
 * @author yumingtao
 * @date 2021/8/22 19:41
 */
public class Solution2 {
    public boolean isPowerOfTwo(int n) {
        if(n <= 0) return false;

        //当n是2的幂次时，n一定可以被2整除
        //不停的用2整除
        while(n != 1){
            //当n对2取模为1的时候，表示不可以整除
            if((n&1) == 1) return false;
            n = n>>1;
        }

        return true;
    }
}
