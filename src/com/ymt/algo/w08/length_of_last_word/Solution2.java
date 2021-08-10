package com.ymt.algo.w08.length_of_last_word;

/**
 * @author yumingtao
 * @date 2021/8/10 11:18
 */
public class Solution2 {
    public int lengthOfLastWord(String s) {
        String[] words = s.split(" ");

        for (int i = words.length - 1; i >= 0; i--) {
            if (words[i] != " ") {
                return words[i].length();
            }
        }

        return 0;
    }
}
