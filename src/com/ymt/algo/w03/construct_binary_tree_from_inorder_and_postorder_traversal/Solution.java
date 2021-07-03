package com.ymt.algo.w03.construct_binary_tree_from_inorder_and_postorder_traversal;

import java.util.Arrays;

/**
 * 思路：
 * 1.后序遍历是左右根，可知最后一个元素是树的根节点
 * 2.根据1中得到的根节点，在中序数组中得到左右子树，然后重复子问题
 * 3.同样使用递归
 * 4.与前序中序构造树类似，只是计算后序遍历数组时不同
 *
 * @author yumingtao
 * @date 7/3/21 5:06 PM
 */
public class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) {
            return null;
        }

        int rootVal = postorder[postorder.length - 1];
        TreeNode root = new TreeNode(rootVal);

        int rootIndex = getRootIndex(inorder, rootVal);
        //计算左右子树的中序，注意索引范围
        int[] leftInorder = Arrays.copyOfRange(inorder, 0, rootIndex);
        int[] rightInorder = Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length);

        //计算左右子树的后序，注意索引范围
        int[] leftPostOrder = Arrays.copyOfRange(postorder, 0, leftInorder.length);
        int[] rightPostOrder = Arrays.copyOfRange(postorder, leftInorder.length, postorder.length - 1);

        root.left = buildTree(leftInorder, leftPostOrder);
        root.right = buildTree(rightInorder, rightPostOrder);

        return root;
    }

    private int getRootIndex(int[] inorder, int rootVal) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootVal) {
                return i;
            }
        }
        return -1;
    }
}

//中序遍历 inorder = [9,3,15,20,7]
//后序遍历 postorder = [9,15,7,20,3]

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