package com.ymt.algo.w08.find_all_anagrams_in_a_string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yumingtao
 * @date 2021/8/19 16:37
 */
public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int k = p.length();
        int n = s.length();

        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();
        Arrays.sort(pArr);
        String sortedP = String.valueOf(pArr);

        List<Integer> ans = new ArrayList<>();

        for(int i = 0; i < n - k + 1; i++){
            char[] sub = Arrays.copyOfRange(sArr, i, i + k);
            Arrays.sort(sub);
            String sortedSub = String.valueOf(sub);

            if(sortedP.equals(sortedSub)){
                ans.add(i);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        /*String s = "cbaebabacd";
        String p = "abc";*/

        String s = "abab";
        String p = "ab";

        Solution solution = new Solution();
        List<Integer> res = solution.findAnagrams(s, p);

        System.out.println(res);
    }
}