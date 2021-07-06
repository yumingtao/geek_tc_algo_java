package com.ymt.algo.w03.letter_combinations_of_a_phone_number;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 思路：
 * 1.参照数组全排列的思路，使用递归来实现
 * 2.判断下个位置可以放什么数据
 *
 * @author yumingta
 * @date 7/5/21 1:53 PM
 */
public class Solution {
    private Map<String, String> digitLetterMapping = new HashMap<>();
    private List<List<String>> result = new ArrayList<>();
    private List<Map<Character, Boolean>> allLetterUsed = new ArrayList<>();
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

        //初始化每个字符对应的数字是否使用过
        for (int i = 0; i < digitChars.length; i++) {
            allLetterUsed.add(new HashMap<>());
        }

        find(0);

        //组装返回结果
        result.stream().forEach(r -> {
            ans.add(r.stream().collect(Collectors.joining("")));
        });

        return ans;
    }

    private void find(int index) {
        //终止条件
        if (index == digitChars.length) {
            result.add(new ArrayList<>(set));
            return;
        }

        //处理逻辑
        //找到digit对应的字符串，并处理成String[]
        char digit = digitChars[index];
        char[] letters = digitLetterMapping.get(String.valueOf(digit)).toCharArray();

        for (char letter : letters) {
            //如果字符已经被用过，则继续寻找
            if (allLetterUsed.get(index).getOrDefault(letter, false)) {
                continue;
            }

            //如果字符没有用过，放入到字符传中
            set.add(String.valueOf(letter));
            allLetterUsed.get(index).put(letter, true);
            find(index + 1);
            allLetterUsed.get(index).put(letter, false);
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
        Solution solution = new Solution();
        solution.letterCombinations(s);
    }
}
