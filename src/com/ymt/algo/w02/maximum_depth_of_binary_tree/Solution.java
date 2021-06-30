package com.ymt.algo.w02.maximum_depth_of_binary_tree;

/**
 * 主体思想：
 * 1. 找出左子树的深度
 * 2. 找出右子树的深度
 * 3. 左右子树最大深度+1
 * 4. 自底向上
 *
 * @author yumingtao
 * @date 6/30/21 10:37 AM
 */
public class Solution {
    public int maxDepth(TreeNode root) {
        //终止条件
        //到达最底层叶子节点
        if (root == null) {
            return 0;
        }

        //进入下一层
        //处理逻辑
        int depth = Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;

        return depth;
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

