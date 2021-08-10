package com.ymt.algo.w08.to_lower_case;

/**
 * @author yumingtao
 * @date 2021/8/10 10:50
 */
public class Solution {
    public String toLowerCase(String s) {
        String newStr = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                newStr += String.valueOf((char) (s.charAt(i) - 'A' + 97));
            } else {
                newStr += String.valueOf(s.charAt(i));
            }
        }

        return newStr;
    }
}
