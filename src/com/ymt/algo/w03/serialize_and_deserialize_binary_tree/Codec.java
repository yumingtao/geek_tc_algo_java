package com.ymt.algo.w03.serialize_and_deserialize_binary_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. 本例遍历采用前序遍历方式序列化
 * 2. 注意遍历到边界时，如果节点没有子节点，以null来表示其子节点
 *
 * @author yumingtao
 * @date 7/2/21 9:37 PM
 */
public class Codec {
    List<String> serializeResult = new ArrayList<>();
    int index = 0;
    String[] values;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        visit(root);
        String result = String.join(",", serializeResult);
        return result;
    }

    private void visit(TreeNode root){
        //到达某个节点的叶子节点，且叶子节点为null，直接添加"null"
        if(root ==null){
            serializeResult.add("null");
            return;
        }

        //采用前序遍历，根左右
        serializeResult.add(String.valueOf(root.val));
        visit(root.left);
        visit(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        values = data.split(",");
        TreeNode node = buildTree();

        return node;
    }

    /**
     * 注意在写递归的时候，关注当前层，要把问题当成一个重复子问题
     * @return
     */
    private TreeNode buildTree(){
        //遇到边界，索引继续向后走，返回null
        if(values[index].equals("null")){
            index++;
            return null;
        }

        //根据前序遍历顺序根左右，递归反序列化时也按照同样的顺序
        TreeNode node = new TreeNode(Integer.valueOf(values[index]));

        index++;
        node.left = buildTree();
        node.right = buildTree();

        return node;
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
// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
