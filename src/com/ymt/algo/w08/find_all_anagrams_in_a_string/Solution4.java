package com.ymt.algo.w08.find_all_anagrams_in_a_string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 思路：使用计数
 *
 * @author yumingtao
 * @date 2021/8/19 16:37
 */
public class Solution4 {
    public List<Integer> findAnagrams(String s, String p) {
        int[] pCount = new int[26];
        Set<Character> pSet = new HashSet<>();
        for (char ch : p.toCharArray()) {
            pCount[ch - 'a']++;
            pSet.add(ch);
        }

        List<Integer> ans = new ArrayList<>();
        char[] sArr = s.toCharArray();
        int[] sCount = new int[26];
        int l = 0;
        int r = 0;

        while (r < s.length()) {
            sCount[sArr[r] - 'a']++;

            //达到窗口长度
            if (r - l + 1 == p.length()) {
                boolean match = true;
                for (char ch : pSet) {
                    int index = ch - 'a';
                    if (pCount[index] != sCount[index]) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    ans.add(l);
                }

                //滑出窗口的元素，count-1
                sCount[sArr[l] - 'a']--;
                l++;
            }
            r++;
        }

        return ans;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";

        /*String s = "abab";
        String p = "ab";*/

        /*String s = "acdcaeccde";
        String p = "c";*/

        Solution4 solution = new Solution4();
        List<Integer> res = solution.findAnagrams(s, p);

        System.out.println(res);
    }
}