package com.ymt.algo.w08.first_unique_character_in_a_string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yumingtao
 * @date 2021/8/10 13:20
 */
public class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int count = map.getOrDefault(s.charAt(i), 0);
            map.put(s.charAt(i), count + 1);
        }

        for (int i = 0; i < n; i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }

        return -1;
    }
}
