package com.ymt.algo.w08.find_all_anagrams_in_a_string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 思路：使用map，朴素整段处理，性能低，超出时间限制
 *
 * @author yumingtao
 * @date 2021/8/19 16:37
 */
public class Solution3 {
    public List<Integer> findAnagrams(String s, String p) {
        int k = p.length();
        int n = s.length();

        Map<Character, Integer> pMap = new HashMap<>();
        for (int i = 0; i < k; i++) {
            pMap.put(p.charAt(i), pMap.getOrDefault(p.charAt(i), 0) + 1);
        }

        List<Integer> ans = new ArrayList<>();
        Map<Character, Integer> sMap = new HashMap<>();

        //滑动窗口，使用双指针，每次整段取出，性能太低
        int l = 0;
        int r = 0;
        int matchCount = 0;
        while (r < n) {
            char ch = s.charAt(r);
            if (pMap.containsKey(ch)) {
                sMap.put(ch, sMap.getOrDefault(ch, 0) + 1);
                if (sMap.get(ch).equals(pMap.get(ch))) {
                    matchCount++;
                }
            }

            //缩短左侧指针，使滑动窗口长度达到p的长度
            if (r - l + 1 == k) {
                if (matchCount == pMap.size()) {
                    ans.add(l);
                }

                char lCh = s.charAt(l);
                //准备将左侧滑出窗口
                if (pMap.containsKey(lCh)) {
                    if (pMap.get(lCh).equals(sMap.get(lCh))) {
                        matchCount--;
                    }
                    if (sMap.containsKey(lCh)) {
                        sMap.put(lCh, sMap.get(lCh) - 1);
                    }
                }

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

        /*String s = "baa";
        String p = "aa";*/

        Solution3 solution = new Solution3();
        List<Integer> res = solution.findAnagrams(s, p);

        System.out.println(res);
    }
}