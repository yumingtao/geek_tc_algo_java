package com.ymt.algo.w01.sliding_window_maximum;

import java.util.*;

/**
 * 思路：
 * 1.使用单调队列模板
 * 2.注意2和3的顺序取决于i是不是侯选项
 * for循环每个元素{
 * //1判断合法性，从时间维度，判断是否过期，下标是否超出范围
 * while(队头过期){
 * 1.1 队头出队
 * }
 * //2取队头为最佳选项，计算答案
 * //3判断队尾的值与新元素是否满足递增或是递减
 * while(队尾与新元素不满足单调性){
 * 3.1 队尾出队
 * }
 * 3.2 新元素入队
 * }
 *
 * @author yumingtao
 * @date 2021/7/19 17:31
 */
public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();

        //使用双端队列，能够从两端去维护队列中的值
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            //1.判断队头下标是否过期
            //过期条件
            while (!queue.isEmpty() && queue.getFirst() <= i - k) {
                //队头出队
                queue.removeFirst();
            }

            //步骤2和3用于维护窗口滑动时，保存最大值，当过期时，最大值为队列中下一个值
            //2.当时间i1<i2，i1比i2优的条件是，nums[i1]>nums[i2]
            //判断队尾与新元素是否满足单调性
            while (!queue.isEmpty() && nums[queue.getLast()] <= nums[i]) {
                //删除队尾
                queue.removeLast();
            }

            //3.将新元素加入到队尾
            queue.add(i);

            //取队头为最佳选项
            //至少满足一个滑动窗口的时候，再添加
            if (i >= k - 1) {
                ans.add(nums[queue.getFirst()]);
            }
        }

        return ans.stream().mapToInt(x -> x).toArray();
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        Solution solution = new Solution();
        int[] b = solution.maxSlidingWindow(a, 3);
        System.out.println();
    }
}
