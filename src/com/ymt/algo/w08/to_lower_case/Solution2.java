package com.ymt.algo.w08.to_lower_case;

/**
 * @author yumingtao
 * @date 2021/8/10 10:55
 */
public class Solution2 {
    public String toLowerCase(String s) {
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] >= 'A' && chs[i] <= 'Z') {
                chs[i] = (char) (chs[i] - 'A' + 97);
            }
        }

        return new String(chs);
    }
}
