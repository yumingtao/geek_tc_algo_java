package com.ymt.algo.w03.n_ary_tree_preorder_traversal;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用递归
 * 前序遍历：根左右，针对n叉树，先处理根，再处理子节点
 *
 * @author yumingtao
 * @date 7/2/21 9:41 AM
 */
public class Solution {
    List<Integer> nodes = new ArrayList<>();

    public List<Integer> preorder(Node root) {
        visit(root);
        return nodes;
    }

    private void visit(Node root) {
        //终止条件
        if(root == null){
            return;
        }

        //处理逻辑，注意遍历顺序，先处理根，再处理自己点
        nodes.add(root.val);
        for (Node node : root.children) {
            visit(node);
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
};
