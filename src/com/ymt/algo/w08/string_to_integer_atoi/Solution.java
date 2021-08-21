package com.ymt.algo.w08.string_to_integer_atoi;

/**
 * @author yumingtao
 * @date 2021/8/21 13:17
 */
public class Solution {
    public int myAtoi(String s) {
        boolean isNegative = false;
        int ans = 0;

        char cur;
        char pre = ' ';
        for (int i = 0; i < s.length(); i++) {
            cur = s.charAt(i);
            if (i >= 1) pre = s.charAt(i - 1);
            if (cur == ' ') {
                if (i >= 1 && pre != ' ') break;
                continue;
            }

            if (cur == '0' && ans == 0) {
                if (i >= 1 && (pre == ' ' || pre == '0')) continue;
            }

            if (isOperator(cur)) {
                if (i >= 1) {
                    if (isOperator(pre) || pre == '0' || isDigit(pre)) break;
                }
                if (cur == '-') isNegative = true;
                continue;
            }

            if (cur > '9' || cur < '0') break;

            int digit = cur - '0';
            if (ans > (Integer.MAX_VALUE - digit) / 10) {
                return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            ans = ans * 10 + digit;
        }

        //处理负数
        if (isNegative) ans = ans * -1;

        return ans;
    }

    private boolean isOperator(char ch) {
        if (ch == '+' || ch == '-') return true;
        return false;
    }

    private boolean isDigit(char ch) {
        if (ch - '0' > 9 || ch - '0' < 0) return false;
        return true;
    }
}
