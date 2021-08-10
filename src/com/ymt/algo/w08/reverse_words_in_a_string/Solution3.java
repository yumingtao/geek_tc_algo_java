package com.ymt.algo.w08.reverse_words_in_a_string;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/***
 * @author yumingtao
 * @date 2021/8/10 19:56
 */
public class Solution3 {
    public String reverseWords(String s) {
        String[] strs = s.split(" ");
        List<String> list = Arrays.stream(strs).filter(str -> !str.isEmpty()).collect(Collectors.toList());
        Collections.reverse(list);
        return String.join(" ", list);
    }
}
