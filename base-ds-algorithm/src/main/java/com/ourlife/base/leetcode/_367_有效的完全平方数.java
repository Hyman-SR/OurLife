package com.ourlife.base.leetcode;

/**
 * https://leetcode-cn.com/problems/valid-perfect-square/
 *
 * @author zhangchao
 * @createdOn 2020/8/1
 */
public class _367_有效的完全平方数 {

    public static void main(String[] args) {
        _367_有效的完全平方数 o = new _367_有效的完全平方数();
//        System.out.println(o.isPerfectSquare_二分法(2147483647));
        System.out.println(o.isPerfectSquare_牛迭(2147483647));
    }

    /**
     * 牛顿迭代法
     *
     * @param num
     * @return
     */
    public boolean isPerfectSquare_牛迭(int num) {
        if (num < 2) {
            return true;
        }
        long x = num / 2;
        while (x * x > num) {
            x = (x + num / x) / 2;
        }
        return (x * x == num);
    }

    /**
     * 注意避免精度溢出
     *
     * @param num
     * @return
     */
    public boolean isPerfectSquare_二分法(int num) {
        if (num < 2) {
            return true;
        }
        long left = 2, right = num / 2, x, guessSquared;
        while (left <= right) {
            x = left + (right - left) / 2;
            guessSquared = x * x;
            if (guessSquared == num) {
                return true;
            }
            if (guessSquared > num) {
                right = x - 1;
            } else {
                left = x + 1;
            }
        }
        return false;
    }
}

