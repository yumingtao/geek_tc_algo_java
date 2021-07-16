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
public class Solution2 {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        //先检索p的关键值
        int val = p.val;
        TreeNode currentNode = root;
        TreeNode ans = null;

        while (currentNode != null) {
            //找到了val所在节点
            if (currentNode.val == val) {
                if (currentNode.right != null) {
                    ans = currentNode.right;
                    TreeNode left = currentNode.right.left;
                    while (left != null) {
                        ans = left;
                        left = left.left;
                    }
                }
                break;
            }

            //判断往左子树还是右子树走
            if (currentNode.val > val) {
                //判断每次遍历过节点的最小值
                if (ans == null || ans.val > currentNode.val) {
                    ans = currentNode;
                }
                //当前节点的值大于val，说明val在左子树
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }

        return ans;
    }
}

