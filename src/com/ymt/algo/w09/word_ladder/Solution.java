package com.ymt.algo.w09.word_ladder;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 思路:普通BFS
 *
 * @author yumingtao
 * @date 2021/8/16 22:09
 */
public class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //到达每个单词需要的距离
        Map<String, Integer> dist = new HashMap<>();
        for (String word : wordList) {
            dist.put(word, (int) 1e9);
        }
        dist.put(beginWord, 1);

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        while (!queue.isEmpty()) {
            String word = queue.poll();

            //针对每个位置的单词，遍历所有的出边
            for (int i = 0; i < word.length(); i++) {
                char[] words = word.toCharArray();

                //循环26个英文字母
                for (char ch = 'a'; ch <= 'z'; ch++) {

                    //如果和当前字符相等，跳过
                    if (words[i] == ch) {
                        continue;
                    }

                    words[i] = ch;
                    String newWord = new String(words);

                    if (dist.containsKey(newWord) && !newWord.equals(word)) {
                        int newDist = dist.get(word) + 1;
                        if (dist.get(newWord) > newDist) {
                            dist.put(newWord, newDist);
                            queue.add(newWord);

                            //找到结果则终止
                            if (newWord.equals(endWord)) {
                                return dist.get(endWord);
                            }
                        }
                    }
                    //还原字符
                    words[i] = word.charAt(i);
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        String[] strs = {"ymann", "yycrj", "oecij", "ymcnj", "yzcrj", "yycij", "xecij", "yecij", "ymanj", "yzcnj", "ymain"};
        List<String> list = Arrays.stream(strs).collect(Collectors.toList());
        Solution solution = new Solution();
        int result = solution.ladderLength("ymain", "oecij", list);
        System.out.println(result);
    }
}
