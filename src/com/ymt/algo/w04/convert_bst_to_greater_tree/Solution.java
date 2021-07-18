package com.ymt.algo.w04.convert_bst_to_greater_tree;

/**
 * 思路：
 * 1.BST的性质，右>根>左
 * 2.要求新值=大于和等于旧值节点的和，应该先计算出所有大于该节点的值的和，再加上该节点做为新节点的值
 * 3.使用右->根->左的顺序深度遍历，全局维护一个sum
 *
 * @author yumingtao
 * @date 2021/7/18 18:44
 */
public class Solution {
    private int sum;

    public TreeNode convertBST(TreeNode root) {
        return dfs(root);
    }

    private TreeNode dfs(TreeNode root) {
        //终止条件
        if (root == null) {
            return null;
        }
        //先递归右子树，计算出sum，好用于更新左子数
        dfs(root.right);
        //sum+当前节点的值做为新值
        sum += root.val;
        root.val = sum;

        //再更新左子树，使用已经计算好的sum，在逐层向上累加
        dfs(root.left);

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
