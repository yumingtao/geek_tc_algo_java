package com.ymt.algo.w08.string_to_integer_atoi;

/**
 * @author yumingtao
 * @date 2021/8/21 14:56
 */
public class Solution2 {
    public int myAtoi(String s) {
        int len = s.length();
        //判断是否是空串
        if (len == 0) return 0;

        int index = 0;
        //处理‘’，, 注意处理边界
        while (index < len && s.charAt(index) == ' ') index++;

        //处理符号, 注意处理边界
        int sign = 1;
        if (index < len && (s.charAt(index) == '+' || s.charAt(index) == '-')) {
            if (s.charAt(index) == '-') sign = -1;
            index++;
        }

        //如果[0, 9]，处理结果，否则终止
        int ans = 0;
        while (index < len) {
            char cur = s.charAt(index);
            //终止条件
            if (cur > '9' || cur < '0') break;

            //判断结果范围
            int digit = cur - '0';
            //注意将ans * 10 + digit > 2147483647移项处理
            //ans * 10 > 2147483647 - digit
            //ans > (2147483647 - digit) / 10
            if (ans > (Integer.MAX_VALUE - digit) / 10) {
                return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }

            //计算结果
            ans = ans * 10 + digit;
            index++;
        }

        return ans * sign;
    }
}
