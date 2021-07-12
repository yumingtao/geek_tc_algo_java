package com.ymt.algo.w03.minimum_genetic_mutation;

import java.util.*;

/**
 * 思路：
 * 1.字符串每个字符有三种选择，字符串的长度是8，一共有24种可能
 * 2.把原字符串看成一个状态，把改变一个字符后的字符串看成另外一个状态，最后所有的状态变化形成图
 * 3.将每一次变化的24种可能看成一个点的出边，求最小变化次数，可以使用BFS
 * 4.变化过程种可能会有重复，需要判重
 *
 * @author yumingtao
 * @date 7/11/21 4:36 PM
 */
public class Solution {
    char[] genes = new char[]{'A', 'C', 'G', 'T'};
    Queue<String> queue = new LinkedList<>();
    //用于判重，可以与depth合并
    Set<String> mutated = new HashSet<>();
    Set<String> geneBank = new HashSet<>();

    public int minMutation(String start, String end, String[] bank) {
        //用于存放字符串所在层数
        Map<String, Integer> depth = new HashMap<>();
        //存储在set中，方便判断基因序列是否在基因库中
        for (String s : bank) {
            geneBank.add(s);
        }

        //BFS模版
        queue.add(start);
        mutated.add(start);
        depth.put(start, 0);

        char[] chars;
        String s;
        char ch;
        while (!queue.isEmpty()) {
            s = queue.poll();
            chars = s.toCharArray();
            //遍历所有的出边
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 4; j++) {
                    //排除自身字符 或是已经访问过
                    if (chars[i] == genes[j]) {
                        continue;
                    }

                    //记录原来的值
                    ch = chars[i];
                    chars[i] = genes[j];
                    String ns = String.valueOf(chars);
                    //还原为原来的值
                    chars[i] = ch;
                    if (!geneBank.contains(ns) || mutated.contains(ns)) {
                        continue;
                    }

                    if (end.equals(ns)) {
                        return depth.get(s) + 1;
                    }
                    queue.add(ns);
                    mutated.add(ns);
                    depth.put(ns, depth.get(s) + 1);
                }
            }
        }

        return -1;
    }
}
