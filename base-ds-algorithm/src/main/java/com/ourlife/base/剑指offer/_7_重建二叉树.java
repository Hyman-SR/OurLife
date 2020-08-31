package com.ourlife.base.剑指offer;

/**
 * https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/
 *
 * @author zhangchao
 * @createdOn 2020/8/22
 */
public class _7_重建二叉树 {

    public static void main(String[] args) {
        _7_重建二叉树 o = new _7_重建二叉树();
        TreeNode root = o.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        System.out.println(111);
    }

//    public TreeNode buildTree_重试(int[] preorder, int[] inorder) {
//
//    }

    /**
     * @param preorder 前序遍历   [3,9,20,15,7]
     * @param inorder  中序遍历    [9,3,15,20,7]
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        if (preorder.length == 1) {
            return new TreeNode(preorder[0]);
        }
        TreeNode root = new TreeNode(preorder[0]);
        int start;
        int[] left_subArrs_preorder = getSubArrs(preorder,
                start = getIndex(preorder, inorder[0]),
                start + getIndex(inorder, preorder[0]) - 1);
        int[] left_subArrs_inorder = getSubArrs(inorder,
                0,
                getIndex(inorder, preorder[0]) - 1);
        root.left = buildTree(left_subArrs_preorder, left_subArrs_inorder);
        int[] right_subArrs_preorder = getSubArrs(preorder,
                getIndex(preorder, inorder[0]) + 1,
                preorder.length - 1);
        int[] right_subArrs_inorder = getSubArrs(inorder,
                getIndex(inorder, preorder[0]) + 1,
                inorder.length - 1);
        root.right = buildTree(right_subArrs_preorder, right_subArrs_inorder);
        return root;
    }

    private int getIndex(int[] arrs, int value) {
        for (int i = 0; i < arrs.length; i++) {
            if (value == arrs[i]) {
                return i;
            }
        }
        throw new IllegalArgumentException();
    }

    private int[] getSubArrs(int[] arrs, int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException();
        }
        int newArrysLenght = end - start + 1;
        int[] newArrs = new int[newArrysLenght];
        for (int i = 0; i < newArrysLenght; i++) {
            newArrs[i] = arrs[start + i];
        }
        return newArrs;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
