package com.ymt.algo.lession01.evaluate_reverse_polish_notation;

import java.util.Stack;

/**
 * 注意在leet code中，使用equals判断 + - * /才能通过
 * 判断字符串相等，一般不用==，而是使用equals，String重写了equals方法
 * @author yumingtao
 * @date 6/20/21 11:42 PM
 */
public class Solution2 {
    public int evalRPN(String[] tokens) {
        //最近相关性，使用栈
        //判断当前字符，是数字则入栈，是运算符，则去除栈中的两个值，进行+—*/，结果再入栈
        //根据题解释，总是合法输入
        Stack<String> stack = new Stack<>();
        int a;
        int b;
        int val = 0;
        for (String s : tokens) {
            if (!s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("/")) {
                stack.push(s);
            } else {
                b = Integer.valueOf(stack.pop());
                a = Integer.valueOf(stack.pop());

                if (s.equals("+")) {
                    val = a + b;
                }
                if (s.equals("-")) {
                    val = a - b;
                }
                if (s.equals("*")) {
                    val = a * b;
                }
                if (s.equals("/")) {
                    val = a / b;
                }

                stack.push(String.valueOf(val));
            }
        }

        return Integer.valueOf(stack.pop());
    }
}
