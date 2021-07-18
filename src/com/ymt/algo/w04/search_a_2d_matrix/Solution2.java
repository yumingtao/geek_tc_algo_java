package com.ymt.algo.w04.search_a_2d_matrix;/**
 * 思路：
 * 1.先二分查找每一个行的第一列，找到<target的最大数所在的行
 * 2.再二分查找改行的元素
 * 3.时间复杂度是logm*logn
 *
 * @author yumingtao
 * @date 2021/7/18 22:44
*/
public class Solution2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        //每一行拼接成了一个单调递增的一维数组
        int m = matrix.length;
        int n = matrix[0].length;

        int left = -1;
        //最后一行的下标是n-1
        int right = m - 1;

        //二分查找每一行中的第一列元素
        //找到<=target的最大的一个数所在的行，target可能在这个行里
        while(left < right){
            int mid = (left + right + 1) / 2;
            int midValue = matrix[mid][0];
            if(midValue < target){
                //说明target在右侧
                left = mid;
            }else if(midValue > target) {
                //说明target可能再左侧
                right = mid - 1;
            }else{
                return true;
            }
        }

        if(right < 0){
            return false;
        }

        //right是所在的行，继续二分查找该行
        int rowIndex = right;
        //改行的行索引是right，列索引0...n
        left = -1;
        right = n - 1;

        while(left < right){
            int mid = (left + right + 1) / 2;
            int midValue = matrix[rowIndex][mid];
            if(midValue < target){
                //说明target在右侧
                left = mid;
            }else if(midValue > target) {
                //说明target可能再左侧
                right = mid - 1;
            }else{
                return true;
            }
        }

        return false;
    }
}
