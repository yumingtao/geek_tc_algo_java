package com.ymt.algo.w08.longest_common_prefix;

import java.util.Arrays;

/**
 * @author yumingtao
 * @date 2021/8/10 14:53
 */
public class Solution2 {
    public String longestCommonPrefix(String[] strs) {
        //按字母自然排序
        Arrays.sort(strs);

        //只需要比较第一个字符串和最后一个字符串即可
        char[] a = strs[0].toCharArray();
        char[] b = strs[strs.length - 1].toCharArray();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < a.length; i++) {
            if (i < b.length && a[i] == b[i]) {
                sb.append(a[i]);
            } else {
                break;
            }
        }

        return sb.toString();
    }
}
