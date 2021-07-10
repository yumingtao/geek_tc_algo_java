package com.ymt.algo.w04.insert_into_a_binary_search_tree;

/**
 * 二叉搜索树
 * 1. 任意节点的左子树的值都比它小
 * 2. 任意节点的右子树的值都比它大
 * 3. 使用递归，可做为模板
 *
 * @author yumingtao
 * @date 7/10/21 10:32 PM
 */
public class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        //终止条件
        //一个节点的左或右没有节点，这个位置就是插入新节点的位置
        if(root == null){
            TreeNode node = new TreeNode(val);
            return node;
        }

        //递归主逻辑
        //判断val与跟节点值的大小
        if(val < root.val){
            //如果val小于根节点的值，继续向左子树插入val
            root.left = insertIntoBST(root.left, val);
        }else{
            //如果val大于根节点的值，继续向右子树插入val
            root.right = insertIntoBST(root.right, val);
        }

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

