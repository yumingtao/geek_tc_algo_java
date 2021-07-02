package com.ymt.algo.w03.n_ary_tree_level_order_traversal;

import java.util.*;

/**
 * 主体思想：
 * 1. 广度遍历使用队列，利用队列的先近先出特性
 * 2. 返回结果需要分层，可以自定以class，包含节点和层号做为属性，队列中存入自定义class对象
 *
 * @author yumingtao
 * @date 7/2/21 7:50 PM
 */
public class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        Queue<LevelNodePair> queue = new LinkedList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();

        //放入根节点
        LevelNodePair lnp = new LevelNodePair(root, 0);
        queue.add(lnp);
        int index;

        while (!queue.isEmpty()) {
            lnp = queue.poll();

            //到达叶子节点终止本轮遍历
            if (lnp.node == null) {
                continue;
            }

            index = lnp.index;
            List<Integer> values = map.getOrDefault(index, new ArrayList<>());
            values.add(lnp.node.val);
            map.put(index, values);

            //将每一层节点和层号pair信息加入队列
            for (Node n : lnp.node.children) {
                queue.add(new LevelNodePair(n, index + 1));
            }
        }

        for (List<Integer> list : map.values()) {
            result.add(list);
        }

        return result;
    }

    class LevelNodePair {
        public Node node;
        public int index;

        public LevelNodePair() {
        }

        public LevelNodePair(Node node, int index) {
            this.node = node;
            this.index = index;
        }
    }
}

/**
 * Definition for a Node.
 */
class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}