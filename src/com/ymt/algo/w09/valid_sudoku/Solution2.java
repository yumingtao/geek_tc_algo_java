package com.ymt.algo.w09.valid_sudoku;

/**
 * 使用数组，执行最快
 *
 * @author yumingtao
 * @date 2021/8/13 15:23
 */
public class Solution2 {
    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] cols = new int[9][9];
        int[][] boxes = new int[9][9];

        int boxIndex;
        int num;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){

                if(board[i][j] == '.'){
                    continue;
                }

                num = board[i][j] - '1';
                boxIndex = i / 3 * 3 + j / 3;
                if(!isValid(rows[i], num) || !isValid(cols[j], num) || !isValid(boxes[boxIndex], num)){
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isValid(int[] arr, int index){
        if(arr[index] == 1){
            return false;
        }else{
            arr[index] = 1;
        }

        return true;
    }
}
