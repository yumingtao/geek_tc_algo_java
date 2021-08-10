package com.ymt.algo.w08.jewels_and_stones;

/**
 * @author yumingtao
 * @date 2021/8/10 11:36
 */
public class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        char[] j = jewels.toCharArray();

        String s = stones;
        for (char ch : j) {
            s = s.replace(String.valueOf(ch), "");
        }

        return stones.length() - s.length();
    }
}
