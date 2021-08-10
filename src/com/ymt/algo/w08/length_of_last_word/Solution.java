package com.ymt.algo.w08.length_of_last_word;

/**
 *
 * @author yumingtao
 * @date 2021/8/10 11:14
 */
public class Solution {
    public int lengthOfLastWord(String s) {
        int ans = 0;
        for(int i = s.length() - 1; i >= 0; i--){
            if(ans > 0 && s.charAt(i) == ' '){
                return ans;
            }else if(s.charAt(i) != ' '){
                ans++;
            }
        }

        return ans;
    }
}
