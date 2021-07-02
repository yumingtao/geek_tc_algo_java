package com.ymt.algo.w03.n_ary_tree_level_order_traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 看了题解，无需自定义结构和使用Map
 *
 * @author yumingtao
 * @date 7/2/21 9:03 PM
 */
public class Solution2 {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        Node node;
        int levelSize;

        if(root == null){
            return result;
        }

        //将根节点入队
        queue.add(root);

        while (!queue.isEmpty()) {
            levelSize = queue.size();

            //创建本层的list，并添加本层的值
            List<Integer> levelList = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                node = queue.poll();

                //到达叶子节点，
                if (node == null) {
                    continue;
                }

                levelList.add(node.val);
                //将本层节点的叶子节点入队
                for (Node leaf : node.children) {
                    queue.add(leaf);
                }
            }
            result.add(levelList);
        }

        return result;
    }
}
