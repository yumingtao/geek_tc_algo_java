package com.ymt.algo.w09.word_ladder;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 思路：双向BFS
 *
 * @author yumingtao
 * @date 2021/8/16 22:27
 */
public class Solution2 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //如果endWord不在wordList，直接返回
        if (!wordList.contains(endWord)) {
            return 0;
        }

        //存放到达每个单词的距离，默认正无穷
        Map<String, Integer> distBegin = new HashMap<>();
        Map<String, Integer> distEnd = new HashMap<>();
        for (String word : wordList) {
            distBegin.put(word, (int) 1e9);
            distEnd.put(word, (int) 1e9);
        }
        //设置开始单词的初始距离
        distBegin.put(beginWord, 1);
        distEnd.put(endWord, 1);

        //双向BFS
        Queue<String> queueBegin = new LinkedList<>();
        queueBegin.add(beginWord);

        Queue<String> queueEnd = new LinkedList<>();
        queueEnd.add(endWord);

        //两个方向的BFS各走一步（各取出队头元素处理）
        //while (!queueBegin.isEmpty() || !queueEnd.isEmpty()) {
        int resultBegin = checkWithBfs(queueBegin, distBegin, distEnd);
        if (resultBegin != -1) {
            return resultBegin;
        }

        int resultEnd = checkWithBfs(queueEnd, distEnd, distBegin);
        if (resultEnd != -1) {
            return resultEnd;
        }
        //}

        return 0;
    }

    private int checkWithBfs(Queue<String> queue, Map<String, Integer> dist, Map<String, Integer> distOther) {
        while (!queue.isEmpty()) {
            String word = queue.poll();
            char[] words = word.toCharArray();

            //遍历每个位置的字符
            for (int i = 0; i < words.length; i++) {
                //遍历所有的出边，26个英文字母
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    //过滤掉自己
                    if (ch == words[i]) {
                        continue;
                    }

                    //替换掉当前字符
                    words[i] = ch;
                    String newWord = new String(words);

                    //判断单词是否在列表里
                    if (dist.containsKey(newWord) && !newWord.equals(word)) {
                        int wordDist = dist.get(word);
                        if (dist.get(newWord) > wordDist + 1) {
                            dist.put(newWord, wordDist + 1);
                            queue.add(newWord);

                            //另一个方向的bfs也处理了newWord，说明两者相遇了
                            if (distOther.get(newWord) != (int) 1e9) {
                                //注意此处dist已经更新过newWord，所以要-1，或直接使用wordDist
                                return dist.get(newWord) + distOther.get(newWord) - 1;
                            }
                        }
                    }

                    //还原
                    words[i] = word.charAt(i);
                }
            }
        }

        //还没有相遇
        return -1;
    }

    //"hit"
    //"cog"
    //["hot","dot","tog","cog"]
    public static void main(String[] args) {
        String[] strs = {"ymann", "yycrj", "oecij", "ymcnj", "yzcrj", "yycij", "xecij", "yecij", "ymanj", "yzcnj", "ymain"};
        //String[] strs = {"hot", "dot", "dog", "lot", "log", "cog"};
        //String[] strs = {"hot", "dot", "tog", "cog"};
        List<String> list = Arrays.stream(strs).collect(Collectors.toList());
        Solution2 solution2 = new Solution2();
        int result = solution2.ladderLength("ymain", "oecij", list);
        //int result = solution2.ladderLength("hit", "cog", list);
        System.out.println(result);
    }
}
