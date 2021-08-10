package com.ymt.algo.w08.reverse_string;

/**
 *
 * @author yumingtao
 * @date 2021/8/10 17:54
 */
public class Solution {
    public void reverseString(char[] s) {
        int l = 0;
        int r = s.length - 1;

        char tmp;
        while(l < r){
            tmp = s[l];
            s[l] = s[r];
            s[r] = tmp;

            l++;
            r--;
        }
    }
}
