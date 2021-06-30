package com.ymt.algo.w02.validate_binary_search_tree;

/**
 * @author mingtao.yu@daimler.com
 * @date 6/30/21 11:06 AM
 */
public class Solution2 {
    public boolean isValidBST(TreeNode root) {
        //对于左子树，根节点是其上限
        //对于右子树，根节点是其下限
        return validate(root.left, Long.MIN_VALUE, root.val) && validate(root.right, root.val, Long.MAX_VALUE);
    }

    /**
     *lowerVal和upperVal限定了节点val的取值范围
     */
    private boolean validate(TreeNode root, long lowerVal, long upperVal) {
        //终止条件
        if (root == null) {
            //递归到最底层叶子节点的叶子节点仍然满足条件
            //直接向上一级递归返回true
            return true;
        }

        //处理逻辑
        //当前节点大于最大值或小于最小值，则返回false
        if (root.val >= upperVal || root.val <= lowerVal) {
            return false;
        }

        return validate(root.left, lowerVal, root.val) && validate(root.right, root.val, upperVal);
    }
}

