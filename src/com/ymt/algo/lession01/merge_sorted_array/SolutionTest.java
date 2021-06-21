package com.ymt.algo.lession01.merge_sorted_array;

/**
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 *
 * @author yumingtao
 * @date 6/18/21 10:13 AM
 */
public class SolutionTest {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};

        Solution solution = new Solution();
        solution.merge(nums1, 3, nums2, 3);

        for (int n : nums1) {
            System.out.println(n);
        }
    }
}
