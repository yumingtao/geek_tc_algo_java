package com.ymt.algo.w01.merge_sorted_array;

/**
 * Description
 *
 * @author yumingtao
 * @date 6/18/21 10:13 AM
 */
public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;

        for (int k = m + n - 1; k >= 0; k--) {
            //i<0->说明nums1已经遍历完，直接复制nums2；同时保证i--边界合法
            //j>=0:保证j--边界合法
            if (i < 0 || (j >= 0 && nums1[i] < nums2[j])) {
                nums1[k] = nums2[j];
                j--;
            } else {
                nums1[k] = nums1[i];
                i--;
            }
        }
    }
}
