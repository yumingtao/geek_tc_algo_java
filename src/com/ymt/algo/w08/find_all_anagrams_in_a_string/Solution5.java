package com.ymt.algo.w08.find_all_anagrams_in_a_string;

import java.util.*;

/**
 * 思路：使用计数
 *
 * @author yumingtao
 * @date 2021/8/19 16:37
 */
public class Solution5 {
    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> pMap = new HashMap<>();
        for (char ch : p.toCharArray()) {
            pMap.put(ch, pMap.getOrDefault(ch, 0) + 1);
        }

        Map<Character, Integer> sMap = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        char[] sArr = s.toCharArray();
        int l = 0;
        int r = 0;

        while (r < s.length()) {
            sMap.put(sArr[r], sMap.getOrDefault(sArr[r], 0) + 1);

            //达到窗口长度
            if (r - l + 1 == p.length()) {
                boolean match = true;
                for (char ch : pMap.keySet()) {
                    if (!sMap.containsKey(ch) || !sMap.get(ch).equals(pMap.get(ch))) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    ans.add(l);
                }

                //滑出窗口的元素，count-1
                sMap.put(sArr[l], sMap.getOrDefault(sArr[l], 0) - 1);
                l++;
            }
            r++;
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

        Solution5 solution = new Solution5();
        List<Integer> res = solution.findAnagrams(s, p);

        System.out.println(res);
    }
}