package com.ymt.algo.w02.validate_binary_search_tree;

/**
 * 主体思想：
 * 1. 左子树的最大值小于根节点
 * 2. 右子树的最小值大于根节点
 * 3. 左右子树分别有左右子树，自顶往下不好判断，采用自底向上判断
 * 4. 需要返回下一层的信息
 *
 * @author yumingtao
 * @date 6/29/21 5:48 PM
 */
public class Solution {
    public boolean isValidBST(TreeNode root) {
        return validate(root).isValid;
    }

    private TreeInfo validate(TreeNode root) {
        //终止条件
        if (root == null) {
            //递归到最底层叶子节点的叶子节点仍然满足条件
            //直接向上一级递归返回true，将最小值设置为最大，将最大值设置为最小
            TreeInfo treeInfo = new TreeInfo(Long.MAX_VALUE, Long.MIN_VALUE, true);
            return treeInfo;
        }

        //进入到下一级递归
        TreeInfo left = validate(root.left);
        TreeInfo right = validate(root.right);

        long minVal = Math.min(Math.min(root.val, left.minVal), right.minVal);
        long maxVal = Math.max(Math.max(root.val, left.maxVal), right.maxVal);

        boolean isValid = false;
        if (left.isValid && right.isValid && left.maxVal < root.val && right.minVal > root.val) {
            isValid = true;
        }

        TreeInfo treeInfo = new TreeInfo(minVal, maxVal, isValid);

        //处理逻辑
        //注意要考虑总的根节点
        return treeInfo;
    }

    class TreeInfo {
        //注意提交答案时存在2147483647，所以最大值最小值设置为long
        //根，左右直接子节点的最小值
        public long minVal;
        //根，左右直接子节点的最大值
        public long maxVal;
        //当前子树是否是二叉搜索树
        public boolean isValid;

        public TreeInfo() {
        }

        public TreeInfo(long minVal, long maxVal, boolean isValid) {
            this.minVal = minVal;
            this.maxVal = maxVal;
            this.isValid = isValid;
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
