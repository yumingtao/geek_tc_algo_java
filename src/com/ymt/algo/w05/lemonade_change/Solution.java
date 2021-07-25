package com.ymt.algo.w05.lemonade_change;

/**
 * 思路：
 * 1.用于找零的只能是5块或10块
 * 2.10是5的倍数，使用10块能找零的，使用5块必然也能找零
 * 3.所以贪心，先从10块开始找零
 *
 * @author yumingtao
 * @date 2021/7/25 02:49
 */
public class Solution {
    public boolean lemonadeChange(int[] bills) {
        int[] counts = new int[5];

        for (int i = 0; i < bills.length; i++) {
            counts[bills[i] / 5]++;
            if (!exchange(bills[i] - 5, counts)) {
                return false;
            }
        }
        return true;
    }

    private boolean exchange(int money, int[] counts) {
        int change = getChange(money, 10, counts);
        change = getChange(change, 5, counts);

        return change == 0;
    }

    private int getChange(int money, int bill, int[] counts) {
        int change = money;
        while (change >= bill && counts[bill / 5] > 0) {
            change -= bill;
            counts[bill / 5]--;
        }

        return change;
    }
}
