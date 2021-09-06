package com.ymt.algo.w01.range_sum_query_2d_immutable;

/**
 * @author yumingtao
 * @date 2021/9/6 21:16
 */
public class NumMatrix2 {
    //对每一行求前缀和
    int[][] sum;

    public NumMatrix2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        //注意下标从1开始
        if (m > 0) {
            sum = new int[m][n];
            //求出矩阵的前缀和数组
            for (int i = 0; i < m; i++) {
                //注意j从1开始
                for (int j = 0; j < n; j++) {
                    //根据公式求每一行的前缀和数组
                    //sum[i][j] = sum[i][j - 1] +  matrx[i][j]
                    sum[i][j] = getSum(i, j - 1) + matrix[i][j];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        //计算每一行的区段和，并累加
        int ans = 0;
        for (int i = row1; i <= row2; i++) {
            ans += sum[i][col2] - getSum(i, col1 - 1);
        }

        return ans;
    }

    private int getSum(int i, int j) {
        return j >= 0 ? sum[i][j] : 0;
    }
}
