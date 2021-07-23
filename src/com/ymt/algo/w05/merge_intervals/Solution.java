package com.ymt.algo.w05.merge_intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 思路:
 * 1.先对二维数组进行排序
 * 2.遍历数组，比较两个区间，如果前一个区间的上限>=后一个区间的下限，则连续
 * 3.一直连续到两个区间上限较大的一个
 *
 * @author yumingtao
 * @date 2021/7/23 16:03
 */
public class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int start1 = o1[0];
                int end1 = o1[1];
                int start2 = o2[0];
                int end2 = o2[1];

                if (start1 > start2 || (start1 == start2 && end1 > end2)) {
                    return 1;
                } else if (start1 < start2 || (start1 == start2 && end1 < end2)) {
                    return -1;
                }
                return 0;
            }
        });

        //设置上下限初始值
        int lower = intervals[0][0];
        int upper = intervals[0][1];

        for (int i = 1; i <= intervals.length - 1; i++) {
            int tempLower = intervals[i][0];
            int tempUpper = intervals[i][1];

            //因为已经排好序了，只需比较前一个上限与后一个下限
            if (upper < tempLower) {
                //前一个上限<后一个下限，已经不连续，将已知连续的区间放入答案
                result.add(new int[]{lower, upper});

                //设置新的区间上下限
                lower = tempLower;
                upper = tempUpper;
            } else {
                //前一个区间和后一个区间连续，比较新的区间上限
                upper = Math.max(upper, tempUpper);
            }
        }

        result.add(new int[]{lower, upper});
        return result.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        //int[][] intervals = new int[][]{{2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}};
        int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        //int[][] intervals = new int[][]{{1, 4}, {4, 5}};
        Solution solution = new Solution();
        int[][] ans = solution.merge(intervals);
        System.out.println();
    }
}