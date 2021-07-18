package com.ymt.algo.w05.guess_number_higher_or_lower;

/**
 * 思路：
 * 1.使用二分，选择一个数验证
 * 2.如果大了，在左半段中使用二分继续选择一个数，继续验证
 * 3.如果小了，在右半段中使用二分继续选择一个数，继续验证
 *
 * 注意：
 * 1.可能超出int的范围，所以需要使用long
 * 2.题解中的方式是left+(right-left)/2来防止溢出
 *
 * @author yumingtao
 * @date 2021/7/17 23:28
 */
public class Solution {
    public int guessNumber(int n) {
        /*long left = 1;
        long right = n;

        while(true){
            long num = (left + right + 1) / 2;
            if(guess((int)num) == 1){
                left = num;
            }else if(guess((int)num) == -1){
                right = num -1;
            }else{
                return (int)num;
            }
        }*/
        int left = 1;
        int right = n;

        while(true){
            //防止计算时溢出
            int num = left + (right - left + 1) / 2;
            if(guess(num) == 1){
                left = num;
            }else if(guess(num) == -1){
                right = num -1;
            }else{
                return num;
            }
        }
    }

    /**
     * 只为了编译能过
     */
    private int guess(int num) {
        int target = 1;
        if(num < target){
            return 1;
        }

        if(num > target){
            return -1;
        }

        return 0;
    }
}
