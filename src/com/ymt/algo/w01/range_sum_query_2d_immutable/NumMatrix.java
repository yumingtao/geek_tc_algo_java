package com.ymt.algo.w01.range_sum_query_2d_immutable;

/**
 * @author yumingtao
 * @date 2021/9/6 20:46
 */
public class NumMatrix {
    //定义二维前缀和数组
    int[][] sum;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        sum = new int[m][n];

        //求出矩阵的前缀和数组
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //System.out.println("i:" + i + ",j:" + j);
                //根据公式
                //sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i - 1][j - 1] + matrx[i][j]
                sum[i][j] = getSum(i - 1, j) + getSum(i, j - 1) - getSum(i - 1, j - 1) + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        //根据二维前缀和公司
        //sum(p,q,i,j) = sum[i][j] - sum[p-1][j] - sum[i][q - 1] + sum[p][q]
        return sum[row2][col2] - getSum(row1 - 1, col2) - getSum(row2, col1 - 1) + getSum(row1 - 1, col1 - 1);
    }

    private int getSum(int i, int j) {
        if (i >= 0 && j >= 0) {
            return sum[i][j];
        }

        return 0;
    }
}
