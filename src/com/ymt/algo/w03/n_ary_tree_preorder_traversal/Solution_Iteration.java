package com.ymt.algo.w03.n_ary_tree_preorder_traversal;

import java.util.*;

/**
 * 使用迭代
 * 主体思想:
 * 1. 根左右，根处理完，处理左边，然后再处理右边
 * 2. 使用栈，再将每层节点倒序，然后利用栈的后进先出特性
 *
 * @author yumingtao
 * @date 7/2/21 9:41 AM
 */
public class Solution_Iteration {
    List<Integer> nodes = new ArrayList<>();
    Stack<Node> stack = new Stack<>();

    public List<Integer> preorder(Node root) {
        Node node;
        stack.add(root);

        //利用队列的先进先出特性，层次遍历树
        while (!stack.isEmpty()) {
            node = stack.pop();

            //注意判断边界
            //如果node为null，说明已经到了叶子节点，直接处理下一个栈顶节点
            if (node == null) {
                continue;
            }
            nodes.add(node.val);

            //倒序节点
            Collections.reverse(node.children);
            for (Node n : node.children) {
                stack.push(n);
            }
        }

        return nodes;
    }
}

