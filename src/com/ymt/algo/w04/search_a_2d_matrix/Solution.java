package com.ymt.algo.w04.search_a_2d_matrix;

/**
 * 思路：
 * 1.矩阵每一行可以拼接成一个单调递增的数组，数组长度m*n
 * 2.针对数组下标可以进行二分查找，时间复杂度logmn
 *
 * 注意：如果是先循环行，根据范围定位到行，然后再针对行进行二分，时间复杂度是mlogn
 *
 * @author yumingtao
 * @date 2021/7/18 21:48
 */
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        //每一行拼接成了一个单调递增的一维数组
        int m = matrix.length;
        int n = matrix[0].length;

        int left = -1;
        //最后一个元素的下标是m*n-1
        int right = m * n - 1;

        //二分查找下标
        while(left < right){
            int mid = (left + right + 1) / 2;
            int midValue = matrix[mid / n][mid % n];
            if(midValue < target){
                //说明target在右侧
                left = mid;
            }else if(midValue > target) {
                //说明target可能再左侧
                right = mid - 1;
            }else{
                //找到相等的返回true
                return true;
            }
        }

        return false;
    }
}
