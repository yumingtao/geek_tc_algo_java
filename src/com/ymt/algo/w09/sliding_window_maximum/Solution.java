package com.ymt.algo.w09.sliding_window_maximum;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Description
 *
 * @author yumingtao
 * @date 2021/8/15 14:38
 */
public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();

        //用来维护顺序，同时value维护每个数字出现的次数
        //key=nums[i], value=计数
        //注意：TreeMap是按key来排序的
        TreeMap<Integer, Integer> treeMap = new TreeMap<>(
                (o1, o2) -> {
                    return o2 - o1;
                }
        );

        //先处理第一次前k个数据
        for (int i = 0; i < k; i++) {
            //统计每个数字的
            int count = treeMap.getOrDefault(nums[i], 0);
            treeMap.put(nums[i], count + 1);
        }
        ans.add(treeMap.firstKey());

        //再处理从k开始的数据
        for (int i = k; i < nums.length; i++) {
            //此时有旧的数据滑进来，如果有则计数+1, 如果没有则计数设置为1
            int count = treeMap.getOrDefault(nums[i], 0);
            treeMap.put(nums[i], count + 1);

            //有数据滑出窗口，判断该数字的计数>1则，count-1，否则删除
            int slidingOutCount = treeMap.getOrDefault(nums[i - k], 0);
            if (slidingOutCount > 1) {
                treeMap.put(nums[i - k], slidingOutCount - 1);
            } else {
                //计数是1或不存在则删除
                treeMap.remove(nums[i - k]);
            }

            //取出当前最大值，加入ans
            ans.add(treeMap.firstKey());
        }

        return ans.stream().mapToInt(x -> x).toArray();
    }
}
