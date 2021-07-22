package com.ymt.algo.w05.relative_sort_array;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 思路：
 * 1.使用计数数组，统计下标出现在arr1中的数字在数组arr1中出现的次数
 *
 * @author yumingtao
 * @date 2021/7/22 17:45
 */
public class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        //存放结果
        int[] ans = new int[arr1.length];
        //结果数组的下标，下标从0开始
        int j = 0;

        //求出数组的最大的数
        int max = Arrays.stream(arr1).max().getAsInt();

        //数组的最大的数+1做为计数数组的长度，这样计数数组的下标可以包含数组中最大的数
        int[] counts = new int[max + 1];
        //计数数组赋值
        for (int i = 0; i < arr1.length; i++) {
            counts[arr1[i]]++;
        }

        //循环arr2，按照arr2数组数字顺序填充结果数组
        for (int num : arr2) {
            while (counts[num] > 0) {
                counts[num]--;
                ans[j++] = num;
            }
        }

        //循环计数数组，将计数不为0的下标填充到结果数组
        for (int i = 0; i < max + 1; i++) {
            while (counts[i] != 0) {
                ans[j++] = i;
                counts[i]--;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        //int[] arr1 = new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        //int[] arr2 = new int[]{2, 1, 4, 3, 9, 6};
        int[] arr1 = new int[]{2, 21, 43, 38, 0, 42, 33, 7, 24, 13, 12, 27, 12, 24, 5, 23, 29, 48, 30, 31};
        int[] arr2 = new int[]{2, 42, 38, 0, 43, 21};
        Solution solution = new Solution();
        int[] ans = solution.relativeSortArray(arr1, arr2);
        System.out.println(Arrays.stream(ans).boxed().collect(Collectors.toList()));
    }
}