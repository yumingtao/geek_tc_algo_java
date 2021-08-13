package com.ymt.algo.w09.valid_sudoku;

import java.util.HashSet;
import java.util.Set;

/**
 * 参考题解，使用1个set，执行最慢
 *
 * @author yumingtao
 * @date 2021/8/13 13:50
 */
public class Solution3 {
    public boolean isValidSudoku(char[][] board) {
        Set<String> set = new HashSet<>();

        int boxIndex;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char ch = board[i][j];

                if (ch == '.') {
                    continue;
                }

                boxIndex = i / 3 * 3 + j / 3;
                if (!isValid(set, generateInfo(ch, "row", i))
                        || !isValid(set, generateInfo(ch, "col", j))
                        || !isValid(set, generateInfo(ch, "box", boxIndex))) {
                    return false;
                }
            }
        }

        return true;
    }

    private String generateInfo(char ch, String type, int index) {
        return String.format("%s in %s [%d]", ch, type, index);
    }

    private boolean isValid(Set<String> set, String info) {
        if (set.contains(info)) {
            return false;
        } else {
            set.add(info);
        }
        return true;
    }
}
