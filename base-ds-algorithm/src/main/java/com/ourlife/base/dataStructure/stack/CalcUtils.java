package com.ourlife.base.dataStructure.stack;

import java.util.Stack;

/**
 * 用双栈来实现：一个栈记录操作数，一个栈记录运算符
 *
 * @author zhangchao
 * @createdOn 2020/8/13
 */
public class CalcUtils {

    private static ThreadLocal<Object> tl = new ThreadLocal<>();
    public static void main(String[] args) {
    }

    public static double calc(String calcExpression) {
        Stack<Double> numStack = new Stack<>();
        Stack<String> operator = new Stack<>();
        return 0;
    }


}
