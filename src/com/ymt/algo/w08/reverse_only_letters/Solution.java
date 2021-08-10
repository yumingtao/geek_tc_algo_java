package com.ymt.algo.w08.reverse_only_letters;

/**
 * @author yumingtao
 * @date 2021/8/10 18:12
 */
public class Solution {
    public String reverseOnlyLetters(String s) {
        char[] chs = s.toCharArray();

        int l = 0;
        int r = chs.length - 1;

        char tmp;

        while (l < r) {
            //不是字母
            if (!isLetter(chs[l])) {
                l++;
                continue;
            }

            //不是字母
            if (!isLetter(chs[r])) {
                r--;
                continue;
            }

            tmp = chs[l];
            chs[l] = chs[r];
            chs[r] = tmp;

            l++;
            r--;
        }

        return new String(chs);
    }

    private boolean isLetter(char ch) {
        if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
            return true;
        }
        return false;
    }
}
