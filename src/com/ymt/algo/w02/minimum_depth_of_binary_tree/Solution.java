package com.ymt.algo.w02.minimum_depth_of_binary_tree;

/**
 * 主体思想：
 * 1. 左右节点都为null，代表叶子节点，返回1
 * 2. 左节点为null，判断右节点深度
 * 3. 右节点为null，判断左节点深度
 * 4. 左右节点都不为null时，min(左子树的最小深度, 右子树的最小深度)
 *
 * 注意递归终止条件模板
 *     if(root == null){
 *         return 0;
 *     }
 * 在递归的时候，深度要加1，因为模板返回的是0
 *
 * @author yumingtao
 * @date 6/30/21 1:39 PM
 */
public class Solution {
    public int minDepth(TreeNode root) {

        //终止条件，模板
        if(root == null){
            return 0;
        }

        if(root.left != null && root.right != null){
            //左右节点都不为null时，min(左子树的最小深度, 右子树的最小深度)，注意要加1
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        }else if (root.left == null) {
            //左节点为null，判断右节点深度，注意要加1
            return minDepth(root.right) + 1;
        }else if(root.right == null){
            //右节点为null，判断左节点深度，注意要加1
            return minDepth(root.left) + 1;
        }

        //左右节点都为null，代表叶子节点，返回1
        return 1;
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