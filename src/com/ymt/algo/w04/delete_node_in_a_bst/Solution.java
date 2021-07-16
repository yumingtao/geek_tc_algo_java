package com.ymt.algo.w04.delete_node_in_a_bst;

/**
 * 思路：
 * 1.先检索到val
 * 2.如果val只有一棵子树，删除val，让子树与val的节点相连
 * 3.找到val的后继，删除后继
 * 4.将后继的值赋值给val
 * 5.使用递归，在以root为根的子节点中删除key，返回新的root
 *
 * @author yumingtao
 * @date 2021/7/16 22:28
 */
public class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        //终止条件
        if(root == null){
            return null;
        }

        //主体逻辑
        if(root.val == key){
            //如果只有右子树
            if(root.left == null){
                //让右子树代替root的位置，返回结束递归
                return root.right;
            }

            //如果只有左子树
            if(root.right == null){
                //让左子树代替root的位置，返回结束递归
                return root.left;
            }

            //如果两棵子树都存在，找到后继
            //右子树，然后一直向左
            TreeNode successor = root.right;
            while(successor.left != null){
                successor = successor.left;
            }
            //删除后继
            root.right = deleteNode(root.right, successor.val);
            //交换root和后继的值
            root.val = successor.val;
            return root;
        }

        //递归到下一层
        if(root.val > key){
            //key在左子树，继续递归到左子树
            root.left = deleteNode(root.left, key);
        }else{
            //key在右子树，继续递归右子树
            root.right = deleteNode(root.right, key);
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
