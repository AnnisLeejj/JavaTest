package com.annis.leetcode.simple;

import com.annis.leetcode.simple.model.TreeNode;

public class TreeTest {
    public static void main(String[] args) {
        TreeTest test = new TreeTest();
        //二叉树的最大深度
        test.maxDepthTest();
    }

    public TreeNode getTree(int val) {
        TreeNode root = new TreeNode(val);
        TreeNode node = setChild(root, val);
        TreeNode nodeL = setChild(node.left, val + 2);
        TreeNode nodeR = setChild(node.right, val + 4);

        return root;
    }

    public TreeNode setChild(TreeNode root, int val) {
        root.left = new TreeNode(val * 10 + 1);
        root.right = new TreeNode(val * 10 + 2);
        return root;
    }

    /**
     * 验证二叉搜索树
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     * <p>
     * 假设一个二叉搜索树具有如下特征：
     * <p>
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     */
    public boolean isValidBST2(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) {
            return true;
        }
        if (root.val <= minVal || root.val >= maxVal) {
            return false;
        }
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }

    //fail
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.val <= root.left.val) {
            return false;
        }
        if (root.right != null && root.val >= root.right.val) {
            return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }

    /**
     * 二叉树的最大深度
     * 给定一个二叉树，找出其最大深度。
     * <p>
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     */

    public void maxDepthTest() {
        TreeNode root = getTree(1);
        System.out.println("深度:" + maxDepth(root));
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int deepLeft = maxDepth(root.left) + 1;
        int deepRight = maxDepth(root.right) + 1;
        return Math.max(deepLeft, deepRight);
    }
}
