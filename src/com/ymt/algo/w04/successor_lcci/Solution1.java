package com.ymt.algo.w04.successor_lcci;

import java.util.ArrayList;
import java.util.List;

/**
 * 思路：
 * 1.BST后继-BST中大于val的最小节点
 * 2.先检索val
 * 3.如果检索到val
 * 3.1 存在右子树，遍历右子树的左子树到底
 * 3.2 不存在右子树，在遍历过的父节点中找大于val的最小值
 * 4.如果没有检索到val，在遍历的父节点中找大于val的最小值
 *
 * @author yumingtao
 * @date 2021/7/16 09:57
 */
public class Solution1 {
    /**
     * 存储遍历时经过的点
     */
    List<TreeNode> visited = new ArrayList<TreeNode>();

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        //先检索p的关键值
        int val = p.val;
        TreeNode node = find(root, val);

        TreeNode ans = null;
        if (node != null && node.right != null) {
            //检索到了val，且val节点右子树不为null
            ans = node.right;
            TreeNode left = node.right.left;
            while (left != null) {
                ans = left;
                left = left.left;
            }
        } else {
            //没有检索到val，或val节点右子树为null
            for(TreeNode n : visited){
                //寻找大于val的最小值
                if(n.val > val){
                    if (ans == null || n.val < ans.val) {
                        ans = n;
                    }
                }
            }
        }

        return ans;
    }

    private TreeNode find(TreeNode root, int val) {
        //终止条件
        if (root == null) {
            //已经遍历完了叶子节点，还没有找到，说明不存在
            return null;
        }

        //处理逻辑
        //记录遍历过的点，其中包括自己
        visited.add(root);

        if (val == root.val) {
            return root;
        }

        if (val > root.val) {
            //val在当前节点的右子树
            return find(root.right, val);
        } else {
            return find(root.left, val);
        }
    }
}

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

