package com.ymt.algo.w05.capacity_to_ship_packages_within_d_days;

import java.util.Arrays;

/**
 * 思路:
 * 1. 船的运载能力的解空间范围[最大包裹重量(一个包裹一个包裹的运送)，全部包裹重量（一次行全部运送）]
 * 2. 使用二分查找枚举解空间
 * 3. 判定解的算法，按照运载能力，判断能否在<=D天内将包裹全部送出
 *
 * @author yumingtao
 * @date 2021/7/21 17:24
 */
public class Solution {
    public int shipWithinDays(int[] weights, int days) {
        //一个一个包裹的送，必须能够装载重量最大的包裹
        int left = Arrays.stream(weights).max().getAsInt();
        //一次全都运送，装载的重量至少要等于全部包裹的重量
        int right = Arrays.stream(weights).sum();

        while (left < right) {
            int mid = (left + right) / 2;

            if (isValid(weights, mid, days)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    private boolean isValid(int[] weights, int capacity, int days) {
        //至少要运送一次，所以取之从1开始
        int totalDays = 1;
        int weightSum = 0;

        for (int i = 0; i < weights.length; i++) {
            weightSum += weights[i];
            if (weightSum <= capacity) {
                //船还能装下，继续装
                continue;
            } else {
                //装不下了，先发一趟
                totalDays++;
                //设置下一船
                weightSum = weights[i];
            }
        }

        return totalDays <= days;
    }

    public static void main(String[] args) {
        int[] weights = new int[]{1, 2, 3, 1, 1};
        Solution solution = new Solution();
        int ans = solution.shipWithinDays(weights, 4);
        System.out.println(ans);
    }
}
