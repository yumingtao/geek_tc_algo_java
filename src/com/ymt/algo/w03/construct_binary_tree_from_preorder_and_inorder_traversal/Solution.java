package com.ymt.algo.w03.construct_binary_tree_from_preorder_and_inorder_traversal;

import java.util.Arrays;

/**
 * 思路：
 * 1.从前序遍历数组中可以得知根节点
 * 2.根据1中得到的节点，可以在中序遍历数组中定位到左子树和右子树
 * 3.针对2中得到的左子树和右子树，重复1和2的过程
 * 4.使用递归
 *
 * @author yumingtao
 * @date 7/3/21 3:35 PM
 */
public class Solution {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0 || inorder.length == 0){
            return null;
        }

        int rootVal = preorder[0];
        TreeNode root = new TreeNode(rootVal);
        int rootIndex = getRootIndex(inorder, rootVal);

        //0~rootIndex-1是左子树
        int[] leftInorder = Arrays.copyOfRange(inorder, 0, rootIndex);
        int[] leftPreorder = Arrays.copyOfRange(preorder, 1, leftInorder.length + 1);

        //rootIndex+1～n是右子树
        int[] rightInorder = Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length);
        int[] rightPreorder = Arrays.copyOfRange(preorder, leftInorder.length + 1, preorder.length);

        root.left = buildTree(leftPreorder, leftInorder);
        root.right = buildTree(rightPreorder, rightInorder);

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

    public static void main(String[] args) {
        int[] preorder = new int[]{3,9,20,15,7};
        int[] inorder = new int[]{9,3,15,20,7};

        Solution solution = new Solution();
        TreeNode treeNode =  solution.buildTree(preorder, inorder);
    }
}

//前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7]

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
