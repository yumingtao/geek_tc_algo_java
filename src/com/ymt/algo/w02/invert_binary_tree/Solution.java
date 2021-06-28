package com.ymt.algo.w02.invert_binary_tree;

/**
 *
 * @author yumingtao
 * @date 6/28/21 11:19 PM
 */
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        //0.终止条件
        //已经到达了最底层的叶子节点
        if(root == null){
            return root;
        }

        //1.处理逻辑
        //左右节点交换
        TreeNode node = root.left;
        root.left = root.right;
        root.right = node;

        //2.进入到下一层
        invertTree(root.left);
        invertTree(root.right);

        //其中1和2和交换顺序，1～2是自顶向下，2～1是自底向上

        return root;
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
