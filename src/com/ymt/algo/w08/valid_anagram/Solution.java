package com.ymt.algo.w08.valid_anagram;

import java.util.Arrays;

/**
 * @author yumingtao
 * @date 2021/8/10 21:11
 */
public class Solution {
    public boolean isAnagram(String s, String t) {
        int m = s.length();
        int n = t.length();

        if (m != n) {
            return false;
        }

        char[] a = s.toCharArray();
        char[] b = t.toCharArray();

        Arrays.sort(a);
        Arrays.sort(b);

        return new String(a).equals(new String(b));
    }
}
