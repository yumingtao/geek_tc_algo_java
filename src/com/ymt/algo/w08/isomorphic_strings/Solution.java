package com.ymt.algo.w08.isomorphic_strings;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yumingtao
 * @date 2021/8/10 20:51
 */
public class Solution {
    public boolean isIsomorphic(String s, String t) {
        return isValid(s, t) && isValid(t, s);
    }

    private boolean isValid(String s, String t){
        int n = s.length();

        Map<Character, Character> letterMapping = new HashMap<>();

        for(int i = 0; i < n; i++){
            char si = s.charAt(i);
            char ti = t.charAt(i);

            if(!letterMapping.containsKey(si)){
                letterMapping.put(si, ti);
            }else if(letterMapping.get(si) != ti){
                return false;
            }
        }

        return true;
    }
}
