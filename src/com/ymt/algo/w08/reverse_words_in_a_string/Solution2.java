package com.ymt.algo.w08.reverse_words_in_a_string;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/***
 * @author yumingtao
 * @date 2021/8/10 19:56
 */
public class Solution2 {
    public String reverseWords(String s) {
        List<String> list = Arrays.asList(s.trim().split("\\s+"));
        Collections.reverse(list);
        return String.join(" ", list);
    }
}
