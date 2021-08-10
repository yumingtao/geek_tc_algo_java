package com.ymt.algo.w08.reverse_words_in_a_string;

import java.util.ArrayList;
import java.util.List;

/***
 * @author yumingtao
 * @date 2021/8/10 19:56
 */
public class Solution {
    public String reverseWords(String s) {
        //首先处理字符串，将单词存入变长数组
        List<String> list = new ArrayList<>();

        int n = s.length();
        int i = 0;
        StringBuilder sb = new StringBuilder();

        while (i < n) {
            if (s.charAt(i) == ' ' && sb.length() != 0) {
                list.add(sb.toString());
                sb = new StringBuilder();
            } else if (s.charAt(i) != ' ') {
                sb.append(s.charAt(i));
            }

            i++;
        }

        if (sb.length() > 0) {
            list.add(sb.toString());
        }

        sb = new StringBuilder();
        //倒序拼接结果
        for (int k = list.size() - 1; k >= 0; k--) {
            sb.append(list.get(k));
            if (k > 0) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }
}
