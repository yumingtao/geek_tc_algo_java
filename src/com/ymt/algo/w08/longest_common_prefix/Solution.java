package com.ymt.algo.w08.longest_common_prefix;

import java.util.Arrays;

/**
 * 思路：
 * 1.对字符数组进行排序，找到长度最小的字符串
 * 2.用长度最小的字符串及其子串分别取匹配其它字符串是否startWith
 *
 * @author yumingtao
 * @date 2021/8/10 13:37
 */
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs,
                (o1, o2) -> {
                    if (o1.length() > o2.length()) {
                        return 1;
                    } else if (o1.length() < o2.length()) {
                        return -1;
                    }
                    return 0;
                });

        String s = strs[0];
        int n = strs.length;

        while (true) {
            int count = n - 1;
            for (int i = 1; i < n; i++) {
                if (!strs[i].startsWith(s)) {
                    break;
                }
                count--;
            }

            if (count > 0 && s.length() > 0) {
                s = s.substring(0, s.length() - 1);
            } else {
                break;
            }
        }

        return s;
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"flower", "flow", "flight"};
        //String[] strs = new String[]{"dog", "racecar", "car"};
        Solution solution = new Solution();
        String result = solution.longestCommonPrefix(strs);
        System.out.println(result);
    }
}
