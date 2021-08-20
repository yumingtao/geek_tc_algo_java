package com.ymt.algo.w08.find_all_anagrams_in_a_string;

import java.util.ArrayList;
import java.util.List;

/**
 * 思路：使用计数
 *
 * @author yumingtao
 * @date 2021/8/19 16:37
 */
public class Solution2 {
    public List<Integer> findAnagrams(String s, String p) {
        int k = p.length();
        int n = s.length();

        int[] pCount = new int[26];
        for (int i = 0; i < k; i++) {
            pCount[p.charAt(i) - 'a']++;
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n - k + 1; i++) {
            int[] sCount = new int[26];
            for (int j = i; j < i + k; j++) {
                sCount[s.charAt(j) - 'a']++;
            }

            int count = 0;
            for (int j = 0; j < k; j++) {
                int index = p.charAt(j) - 'a';
                if (sCount[index] != pCount[index]) {
                    break;
                }

                count++;
            }

            if (count == k) {
                ans.add(i);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        /*String s = "cbaebabacd";
        String p = "abc";*/

        /*String s = "abab";
        String p = "ab";*/

        String s = "acdcaeccde";
        String p = "c";

        Solution2 solution = new Solution2();
        List<Integer> res = solution.findAnagrams(s, p);

        System.out.println(res);
    }
}