package com.ymt.algo.w08.implement_strstr;

/**
 * 朴素算法，效率较低
 *
 * @author yumingtao
 * @date 2021/8/21 18:05
 */
public class Solution {
    public int strStr(String haystack, String needle) {
        int nLen = needle.length();
        if (nLen == 0) {
            return 0;
        }

        int l = 0;
        int r = nLen;
        String sub;
        while (r <= haystack.length()) {
            sub = haystack.substring(l, r);
            if (sub.equals(needle)) return l;
            l++;
            r++;
        }

        return -1;
    }
}
