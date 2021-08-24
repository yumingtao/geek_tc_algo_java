package com.ymt.algo.w10.the_skyline_problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

/***
 * @author yumingtao
 * @date 2021/8/24 15:21
 */
public class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        //使用TreeMap，维护地点从小到大的顺序
        List<Event> events = new ArrayList<>();
        //先将输入构造成event事件
        Event event;
        for (int[] b : buildings) {
            int left = b[0];
            int right = b[1];
            int height = b[2];
            event = new Event(left, height);
            events.add(event);
            event = new Event(right, -height);
            events.add(event);
        }

        //对events排序
        Collections.sort(events, Comparator.comparingInt(o -> o.place));

        List<List<Integer>> ans = new ArrayList<>();
        //key-高度，value-高度出现的次数
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < events.size(); i++) {
            int place = events.get(i).place;
            int height = events.get(i).action;
            if (height > 0) {
                map.put(height, map.getOrDefault(height, 0) + 1);
            } else {
                int H = -height;
                int count = map.get(H);
                if (count > 1) {
                    map.put(H, count - 1);
                } else {
                    //H的次数为1或0的时候，从map中删除
                    map.remove(H);
                }
            }

            //当到达最后一个点结束
            //或没有达到最后一个点，而且当前点与下一个点不重叠
            if (i == events.size() - 1 || place != events.get(i + 1).place) {
                int curMaxHeight;
                if (map.isEmpty()) {
                    //达到最后一个点，map空了,最大高度为0
                    curMaxHeight = 0;
                } else {
                    //map没有空，返回当前最大高度
                    curMaxHeight = map.lastKey();
                }

                if (ans.isEmpty() || curMaxHeight != ans.get(ans.size() - 1).get(1)) {
                    List<Integer> list = Arrays.stream(new int[]{place, curMaxHeight}).boxed().collect(Collectors.toList());
                    ans.add(list);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        //int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        int[][] buildings = {{0, 2, 3}, {2, 5, 3}};
        List<List<Integer>> res = solution.getSkyline(buildings);
        System.out.println(res);
    }
}

class Event {
    public int place;
    public int action;

    public Event(int place, int action) {
        this.place = place;
        this.action = action;
    }
}
