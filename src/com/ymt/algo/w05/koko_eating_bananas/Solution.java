package com.ymt.algo.w05.koko_eating_bananas;

import java.util.Arrays;

/**
 * 思路:
 * 1. 吃香蕉速度的解空间[1(至少要吃掉1根香蕉), 最大一堆香蕉的数量]，速度再大了也没有用，一个小时一次只能吃掉一堆
 * 2. 判定解是否合法=>按照解的速度吃香蕉，所用的时间是否<=给定的时间H
 * 3. 使用二分法枚举解空间
 *
 * @author yumingtao
 * @date 2021/7/21 20:39
 */
public class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        //速度至少是吃掉一根香蕉
        int left = 1;
        //最大速度是一次吃掉香蕉数量最多一堆，速度再大了也没有用，一个小时一次只能吃掉一堆
        int right = Arrays.stream(piles).max().getAsInt();

        while (left < right) {
            //piles[i]<=10^9,left+right在int范围
            int mid = (left + right) / 2;
            if (isValid(piles, h, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    private boolean isValid(int[] piles, int h, int eatingSpeed) {
        //注意次数从0开始累加时间
        int eatingHours = 0;
        for (int i = 0; i < piles.length; i++) {
            if (piles[i] <= eatingSpeed) {
                //如果香蕉的数量<=吃香蕉的速度，吃香蕉所用的时间+1
                eatingHours++;
            } else {
                //香蕉的数量>吃香蕉的速度，数量/速度，向上取整
                //此处要注意整除的情况
                eatingHours += (piles[i] + eatingSpeed - 1) / eatingSpeed;
            }
        }

        return eatingHours <= h;
    }

    public static void main(String[] args) {
        int[] piles = new int[]{1000000000};
        Solution solution = new Solution();
        int eatingSpeed = solution.minEatingSpeed(piles, 2);
        System.out.println(eatingSpeed);
    }

}