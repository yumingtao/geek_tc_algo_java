package com.ymt.algo.w07.implement_trie_prefix_tree;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author yumingtao
 * @date 2021/7/31 23:03
 */
public class Trie {
    private Node root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node current = root;
        //循环word的给一个字符
        for(int i = 0; i < word.length(); i++){
            Character ch = word.charAt(i);
            //如果不存在，则创建
            if(!current.children.containsKey(ch)){
                current.children.put(ch, new Node());
            }

            current = current.children.get(ch);
        }

        //创建完成后，word的词频count++
        current.count++;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node current = root;
        //循环word的给一个字符
        for(int i = 0; i < word.length(); i++){
            Character ch = word.charAt(i);
            //如果不存在，直接返回false
            if(!current.children.containsKey(ch)){
                return false;
            }

            //如果存在，查找下一个字符是否存在
            current = current.children.get(ch);
        }

        //最后查看word的词频是否>0
        return current.count > 0;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node current = root;
        //循环word的给一个字符
        for(int i = 0; i < prefix.length(); i++){
            Character ch = prefix.charAt(i);
            //如果不存在，直接返回false
            if(!current.children.containsKey(ch)){
                return false;
            }

            //如果存在，查找下一个字符是否存在
            current = current.children.get(ch);
        }

        //遍历完prefix，直接返回true
        return true;
    }

    class Node{
        //词频
        public int count;
        //出边
        public Map<Character, Node> children;

        public Node(){
            this.count = 0;
            this.children = new HashMap<>();
        }
    }
}
