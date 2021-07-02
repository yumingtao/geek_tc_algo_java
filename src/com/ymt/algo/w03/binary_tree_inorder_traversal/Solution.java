package com.ymt.algo.w03.binary_tree_inorder_traversal;

import java.util.ArrayList;
import java.util.List;

/**
 * 中序遍历:左根右
 * 深度遍历使用递归
 * @author yumingtao
 * @date 7/2/21 8:49 AM
 */
public class Solution {
    List<Integer> nodes = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        visit(root);
        return nodes;
    }

    private void visit(TreeNode root){
        //终止条件
        if(root == null){
            return;
        }

        //处理逻辑，注意顺序左根右
        visit(root.left);
        nodes.add(root.val);
        visit(root.right);
    }
}

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
