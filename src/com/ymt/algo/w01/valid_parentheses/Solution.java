package com.ymt.algo.w01.valid_parentheses;

import java.util.Stack;

/**
 * Description
 *
 * @author yumingtao
 * @date 6/20/21 8:44 PM
 */
public class Solution {
    public boolean isValid(String s) {
        //最近相关性，使用栈
        //考虑边界

        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();

        for (char ch : chars) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char temp = stack.pop();
                if (ch == ')' && temp != '(') {
                    return false;
                }
                if (ch == ']' && temp != '[') {
                    return false;
                }
                if (ch == '}' && temp != '{') {
                    return false;
                }
            }
        }

        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }
}
