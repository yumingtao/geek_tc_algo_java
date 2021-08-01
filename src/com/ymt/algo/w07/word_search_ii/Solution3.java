package com.ymt.algo.w07.word_search_ii;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 思路：
 * 1.将words构造成字典树
 * 2.dfs遍历二维字符网格, 查找组成的字符串是否在字典树之中存在
 * 3.优化：删除没有子节点的无用节点
 *
 * @author yumingtao
 * @date 2021/8/1 13:38
 */
public class Solution3 {
    private final int[] dx = new int[]{-1, 0, 0, 1};
    private final int[] dy = new int[]{0, 1, -1, 0};
    private boolean[][] visited;
    private TrieNode root;
    private int m;
    private int n;
    private List<String> ans;

    public List<String> findWords(char[][] board, String[] words) {
        ans = new ArrayList();
        //先构造单词的字典树
        root = new TrieNode();
        for(String word : words){
            insert(word);
        }

        //校验字典树是否创建成功
        //System.out.println(root.children.size());

        m = board.length;
        n = board[0].length;
        //枚举所有的起始点，DFS，找出能够组成的单词
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                visited = new boolean[m][n];
                //注意在dfs中，要先标记已访问
                visited[i][j] = true;
                dfs(board, i, j, root);
            }
        }

        return ans;
    }

    private void dfs(char[][] board, int x, int y, TrieNode current){
        //终止条件
        Character ch = board[x][y];
        //如果当前节点没有子节点，直接返回
        if(!current.children.containsKey(ch)){
            return;
        }

        TrieNode fa = current;
        current = fa.children.get(ch);
        if(current.word != null){
            //说明已经找到了word
            ans.add(current.word);
            //单词用过一次就没用了，防止重复
            current.word = null;
        }

        //查看当前这个节点是否还有子节点
        if(current.children.isEmpty()){
            fa.children.remove(ch);
        }

        //处理逻辑
        //遍历四个出边
        for(int k = 0; k < 4; k++){
            int nx = x + dx[k];
            int ny = y + dy[k];

            if(nx < 0 || ny < 0 || nx >= m || ny >= n || visited[nx][ny]){
                continue;
            }

            visited[nx][ny] = true;

            //递归遍历nx,ny
            dfs(board, nx, ny, current);

            visited[nx][ny] = false;
        }

    }

    /**
     * 字典树中插入单词
     */
    private void insert(String word){
        TrieNode current = root;

        for(int i = 0; i < word.length(); i++){
            Character ch = word.charAt(i);

            if(!current.children.containsKey(ch)){
                current.children.put(ch, new TrieNode());
            }

            //继续向下寻找
            current = current.children.get(ch);
        }

        current.word = word;
    }

    /**
     * 字典树节点类
     */
    class TrieNode{
        public String word;

        //出边
        public Map<Character, TrieNode> children;

        public TrieNode(){
            this.word = null;
            this.children = new HashMap<>();
        }
    }
}
