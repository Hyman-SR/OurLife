package com.ourlife.base.leetcode.p2;

import java.util.Stack;

/**
 * 给出的n个非负整数表示每个直方图的高度，每个直方图的宽均为1，在直方图中找到最大的矩形面试
 *
 * @author zhangchao
 * @createdOn 2020/6/29
 */
public class P2_LargestRectangleArea {

    public static void main(String[] args) {

//        int[] height = new int[]{1, 2, 3, 4, 5, 5, 5, 4, 3, 2, 4, 5, 6, 8, 3, 2, 2};
//        System.out.println(Arrays.toString(height));
//        System.out.println(getLargestRectangleArea(height));
    }

    public static int getLargestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        //为什么从0开始，还要<=操作？需要将所有的直方图全部压入栈中，再从中一个一个获取去计算
        for (int i = 0; i <= height.length; i++) {
            int nowRectangle = -1;
            if (i < height.length) {
                //记录当前直方图面积
                nowRectangle = height[i];
            }
            //当直方图面积开始降序，且栈不为空时，则开始获取前边升序部分的最大矩形面积
            while (!stack.isEmpty() && nowRectangle < height[stack.peek()]) {
                int thisHeight = height[stack.pop()];
                int thisWidth = i;
                if (!stack.isEmpty()) {
                    thisWidth = i - stack.peek() - 1;
                }
                max = Math.max(max, thisHeight * thisWidth);
                System.out.println("i = " + i + "; max=" + max + "; thisHeight * thisWidth = " + thisHeight + " * " + thisWidth + " = " + thisHeight * thisWidth);
            }
            System.out.println("将当前序号压入栈中=" + i);
            stack.push(i);
        }
        return max;
    }

}
