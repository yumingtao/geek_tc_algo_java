package com.ymt.algo.w03.lowest_common_ancestor_of_a_binary_tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 思路:
 * 1.采用向上标记法，将第一个节点的父节点标记一遍
 * 2.在向上寻找另一个节点的所有父亲节点，当第一次遇到和第一个节点的父亲节点一样的节点时，就是答案
 * 3.关键是求出每个节点的父亲节点
 *
 * @author yumingtao
 * @date 7/3/21 7:44 PM
 */
public class Solution {
    Map<Integer, TreeNode> fatherMapping = new HashMap<>();
    Set<TreeNode> markedFathers = new HashSet<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //首先递归求出每个节点的父亲节点
        //一个节点可以是它自己的祖先
        findFather(root, root);

        //根节点是必经的父节点
        markedFathers.add(root);

        //循环标记第一个节点的父节点
        while (p.val != root.val){
            markedFathers.add(p);
            //找到p的父节点
            p = fatherMapping.get(p.val);
        }

        //循环第二个节点
        while (!markedFathers.contains(q)){
            q = fatherMapping.get(q.val);
        }

        return q;
    }

    private void findFather(TreeNode root, TreeNode fatherNode){
        if(root == null){
            return;
        }

        fatherMapping.put(root.val, fatherNode);

        //处理左子树
        findFather(root.left, root);

        //处理右子树
        findFather(root.right, root);
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

