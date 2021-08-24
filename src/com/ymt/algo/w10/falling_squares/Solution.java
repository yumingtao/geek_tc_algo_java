package com.ymt.algo.w10.falling_squares;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yumingtao
 * @date 2021/8/24 17:24
 */
public class Solution {
    public List<Integer> fallingSquares(int[][] positions) {
        //构造Square数组
        Square[] squares = new Square[positions.length];
        for (int i = 0; i < positions.length; i++) {
            int left = positions[i][0];
            int right = positions[i][0] + positions[i][1];
            int height = positions[i][1];

            squares[i] = new Square(left, right, height);
        }

        //存放每个方块掉下来时对应得到高度
        List<Integer> heights = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        heights.add(squares[0].height);
        ans.add(squares[0].height);
        //循环Square数组
        for (int i = 1; i < squares.length; i++) {
            Square cur = squares[i];

            int curMaxHeight = cur.height;
            //一次向前查找，看是否有重叠
            for (int j = i - 1; j >= 0; j--) {
                Square pre = squares[j];
                if (cur.left >= pre.right || cur.right <= pre.left) {
                    //没有重叠
                    continue;
                } else {
                    //重叠了
                    curMaxHeight = Math.max(curMaxHeight, cur.height + heights.get(j));
                }
            }
            heights.add(curMaxHeight);
            curMaxHeight = Math.max(curMaxHeight, ans.get(ans.size() - 1));
            ans.add(curMaxHeight);
        }

        return ans;
    }

    class Square {
        public int left;
        public int right;
        public int height;

        public Square(int left, int right, int height) {
            this.left = left;
            this.right = right;
            this.height = height;
        }
    }

    //[[9,6],[2,2],[2,6]]
    public static void main(String[] args) {
        //int[][] squares = {{1, 2}, {2, 3}, {6, 1}};
        //int[][] squares = {{100, 100}, {200, 100}};
        //int[][] squares = {{6, 1}, {9, 2}, {2, 4}};
        //int[][] squares = {{7, 1}, {3, 3}, {7, 5}};
        int[][] squares = {{9, 6}, {2, 2}, {2, 6}};
        Solution solution = new Solution();
        List<Integer> ans = solution.fallingSquares(squares);
        System.out.println(ans);
    }
}
