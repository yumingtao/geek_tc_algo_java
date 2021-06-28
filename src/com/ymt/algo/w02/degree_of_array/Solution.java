package com.ymt.algo.w02.degree_of_array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 主体思想：
 * 1. 通过map记录数组中每个元素和出现的位置链表，key为元素值，value为下标链表
 * 2. 找出map中的最长链表，即为数组的度
 * 3. 计算链表头尾最大跨度
 *
 * @author yumingtao
 * @date 6/28/21 9:14 PM
 */
public class Solution {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        //通过map统计每个元素出现的次数
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i]).add(i);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(nums[i], list);
            }
        }

        //找到数组的度
        int degree = 1;
        for (List<Integer> list : map.values()) {
            degree = Integer.max(list.size(), degree);
        }

        //在出现次数等于数组的度的元素中找到最小连续子数组
        //判断条件时链表最后一个元素于第一个元素差值最小
        int result = Integer.MAX_VALUE;
        for (List<Integer> list : map.values()) {
            if (list.size() == degree) {
                result = Integer.min(list.get(list.size() - 1) - list.get(0) + 1, result);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 2, 3, 1};
        Solution solution = new Solution();
        solution.findShortestSubArray(nums);
    }
}
