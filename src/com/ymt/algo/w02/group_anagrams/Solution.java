package com.ymt.algo.w02.group_anagrams;

import java.util.*;

/**
 * 主体思想：
 * 1. 对每个字符串排序，如果是字母异位词，排序后的字符串值相同
 * 2. 使用Map<String, List<String>>存储，key为排序后的字符串，value为原字符串链表
 *
 * @author yumingtao
 * @date 7/1/21 9:21 AM
 */
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> resultMap = new HashMap<>(strs.length);
        List<List<String>> results = new ArrayList<>();
        String orderedStr;
        List<String> groups;

        for (String s : strs) {
            orderedStr = sortString(s);
            if(resultMap.containsKey(orderedStr)){
                resultMap.get(orderedStr).add(s);
            }else{
                groups = new ArrayList<>();
                groups.add(s);
                resultMap.put(orderedStr, groups);
            }
        }

        for(List<String> group: resultMap.values()){
            results.add(group);
        }

        return results;
    }

    private String sortString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }
}