package com.ymt.algo.w03.letter_combinations_of_a_phone_number;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 思路：
 * 1.参照子集的问题
 * 2.使用图的dfs
 *
 * @author yumingta
 * @date 7/5/21 1:53 PM
 */
public class Solution2 {
    private Map<String, String> digitLetterMapping = new HashMap<>();
    private List<List<String>> result = new ArrayList<>();
    private List<String> set = new ArrayList<>();
    char[] digitChars;

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.equals("")) {
            return ans;
        }

        //先构造数字字母mapping
        initLetterDigitMapping();
        digitChars = digits.toCharArray();
        dfs(0);

        result.stream().forEach(r -> {
            ans.add(r.stream().collect(Collectors.joining("")));
        });

        return ans;
    }

    private void dfs(int index) {
        //终止条件
        if (index == digitChars.length) {
            result.add(new ArrayList<>(set));
            return;
        }

        //处理逻辑
        //找到digit对应的字符串，并处理成char[]
        char digit = digitChars[index];
        char[] letters = digitLetterMapping.get(String.valueOf(digit)).toCharArray();
        //遍历所有的出边
        for (char letter : letters) {
            set.add(String.valueOf(letter));
            dfs(index + 1);
            set.remove(set.size() - 1);
        }
    }


    private void initLetterDigitMapping() {
        digitLetterMapping.put("2", "abc");
        digitLetterMapping.put("3", "def");
        digitLetterMapping.put("4", "ghi");
        digitLetterMapping.put("5", "jkl");
        digitLetterMapping.put("6", "mno");
        digitLetterMapping.put("7", "pqrs");
        digitLetterMapping.put("8", "tuv");
        digitLetterMapping.put("9", "wxyz");
    }

    public static void main(String[] args) {
        String s = "999";
        Solution2 solution = new Solution2();
        solution.letterCombinations(s);
    }
}
