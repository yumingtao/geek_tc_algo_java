package com.ymt.algo.w03.n_queens_ii;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 思路:
 * 1. n行n列，放n个皇后，同行同列会互相攻击，先把问题转换成在n*n的格子里，每一行每一列只放一个Q，有多少种方式
 * 2. 进一步转换为，在0...n-1个位置中放0...n-1中的一个数，每个位置不能重复，有多少种组合
 * 3. 即n长数组，放入连续的那个数，每个位置不同，有多少种组合
 * 4. 每种组合满足了不在同一行同一列，还需要进一步判断不能在两个对角线方向
 * 5. 对角线左上->右下，满足i-j的值都相等
 * 6. 对角线右上->左下，满足i+j的值都相等
 *
 * @author yumingtao
 * @date 7/9/21 3:06 PM
 */
public class Solution {
    private boolean[] isNumUsed;
    private List<Integer> set = new ArrayList<>();
    private List<List<Integer>> permutation = new ArrayList<>();

    public int totalNQueens(int n) {
        //初始化数字是否已使用数组，默认值都是false
        isNumUsed = new boolean[n];
        //先对1...n放入n上数组做全排列，得到每行每列可以放Q的位置
        findPermutation(0, n);

        int result = 0;
        //循环找到的结果，进行两个斜线方向上的判断
        for (List<Integer> p : permutation) {
            if (isValid(p)) {
                result++;
            }
        }

        return result;
    }

    private boolean isValid(List<Integer> list) {
        Set<Integer> indexPlusSet = new HashSet<>();
        Set<Integer> indexSubtractSet = new HashSet<>();
        //i为列号，list.get(i)为行号
        for (int i = 0; i < list.size(); i++) {
            int j = list.get(i);
            int indexPlus = i + j;
            int indexSubtract = i - j;

            if (indexPlusSet.contains(indexPlus) || indexSubtractSet.contains(indexSubtract)) {
                return false;
            }

            indexPlusSet.add(indexPlus);
            indexSubtractSet.add(indexSubtract);
        }

        return true;
    }

    //使用递归找到排列组合
    private void findPermutation(int index, int n) {
        //终止条件
        if (index == n) {
            permutation.add(new ArrayList<>(set));
            return;
        }

        //处理逻辑
        for (int i = 0; i < n; i++) {
            if (isNumUsed[i]) {
                continue;
            }

            set.add(i);
            isNumUsed[i] = true;
            findPermutation(index + 1, n);
            isNumUsed[i] = false;
            set.remove(set.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.totalNQueens(8);
    }
}
