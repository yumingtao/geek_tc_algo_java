package com.ymt.algo.w01.evaluate_reverse_polish_notation;

import java.util.Stack;

/**
 * @author yumingtao
 * @date 6/20/21 11:05 PM
 */
public class Solution {
    public int evalRPN(String[] tokens) {
        //最近相关性，使用栈
        //判断当前字符，是数字则入栈，是运算符，则去除栈中的两个值，进行+—*/，结果再入栈
        //根据题解释，总是合法输入
        Stack<String> stack = new Stack<>();
        int a;
        int b;
        int val = 0;
        for (String s : tokens) {
            if (s != "+" && s != "-" && s != "*" && s != "/") {
                stack.push(s);
            } else {
                b = Integer.valueOf(stack.pop());
                a = Integer.valueOf(stack.pop());

                if (s == "+") {
                    val = a + b;
                }
                if (s == "-") {
                    val = a - b;
                }
                if (s == "*") {
                    val = a * b;
                }
                if (s == "/") {
                    val = a / b;
                }
                stack.push(String.valueOf(val));
            }
        }

        return Integer.valueOf(stack.pop());
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] tokens = new String[]{"2", "1", "+", "3", "*"};
        System.out.println(solution.evalRPN(tokens));
    }
}
