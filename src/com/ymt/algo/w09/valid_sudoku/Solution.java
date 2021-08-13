package com.ymt.algo.w09.valid_sudoku;

import java.util.HashSet;
import java.util.Set;

/**
 * 使用3个set
 * @author yumingtao
 * @date 2021/8/13 13:50
 */
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<Character>[] rows = new Set[9];
        Set<Character>[] cols = new Set[9];
        Set<Character>[] boxes = new Set[9];

        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        int boxIndex;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char ch = board[i][j];

                if (ch == '.') {
                    continue;
                }

                boxIndex = i / 3 * 3 + j / 3;
                if (!isValid(rows[i], ch) || !isValid(cols[j], ch) || !isValid(boxes[boxIndex], ch)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isValid(Set<Character> set, char ch) {
        if (set.contains(ch)) {
            return false;
        } else {
            set.add(ch);
        }
        return true;
    }
}
