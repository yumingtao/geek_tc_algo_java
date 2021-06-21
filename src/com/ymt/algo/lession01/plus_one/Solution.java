package com.ymt.algo.lession01.plus_one;

/**
 * Description
 *
 * @author yumingtao
 * @date 6/20/21 5:48 PM
 */
public class Solution {
    public int[] plusOne(int[] digits) {
        //最后一位数组元素加1
        //当数组元素是9，加1为10，进位，当前元素为0，上个元素+1，也可能会导致进位，多米诺骨牌效应，如 9，99，999
        //此时数组需要扩一个长度，首元素是1，其余都是0
        //倒序处理

        int len = digits.length;
        for (int i = len - 1; i >= 0; i--) {
            digits[i]++;
            if(digits[i] != 10){
                return digits;
            }
            digits[i] = 0;
        }

        //此时出现9，99，999情况，digits全是0，需要一个新数组，首元素是1，其余是0
        digits = new int[len + 1];
        digits[0] = 1;
        return digits;
    }
}
