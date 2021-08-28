package com.ymt.algo.w07.max_value_of_equation;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 由于x单调递增，假设j<i，通过优化最终推导出下边的核心逻辑
 * int ans = (int)-1e9;
 * for(int i = 0; i < n; i++){
 *  int temp = ∞
 *  for(int j = 下界; j <= 上界; j++ ){
 *  temp = max(temp, y[j] - x[j])
 *  }
 *  ans = max(ans, y[i] + x[i] + temp)
 * }
 * 考虑x[i]-x[j]<=k时，即x[j]>=x[i]-k，i单调递增时，x[i]在单调递增，k不变，所以x[j]是单调递增，j单调递增，是一个滑动窗口
 * 所以核心问题是i增加时，求在j满足上下界条件时，滑动窗口中y[j]-x[j]的最大值
 *
 * @author yumingtao
 * @date 2021/8/28 22:03
 */
public class Solution {
    public int findMaxValueOfEquation(int[][] points, int k) {
        //直接使用单调队列求解滑动窗口最大值的模版
        //j的上界是i-1，下界满足x[j]>=x[i]-k
        //当j变化时，j1<j2,此时j1比j2优的条件是y[j1]-x[j1]>y[j2]-x[j2]
        //即j1下标虽小，但是只要没滑出范围，它满足在x[j]>=x[i]-k时，有最大值

        int ans = (int)-1e9;
        //双端队列存储下标
        Deque<Integer> q = new LinkedList<>();

        for(int i = 0; i < points.length; i++){
            //1.判断队头合法性，判断x[j]>=x[i]-k
            //x[j]是points[j][0], x[i]是points[i][0]
            while(!q.isEmpty() && points[q.getFirst()][0] < points[i][0] - k){
                //不满足条件是，直接丢弃
                q.removeFirst();
            }

            //2.如果队头合法，取队头为最优解即最大值
            if(!q.isEmpty()){
                int j = q.getFirst();
                //ans = max(ans, y[i] + x[i] + y[j] - x[j])
                //x[j]是points[j][0], x[i]是points[i][0]
                //y[j]是points[j][1], y[i]是points[i][1]
                ans = Math.max(ans, points[i][1] + points[i][0] + points[j][1] - points[j][0]);
            }

            //3.维护队列单调性,在队尾插入新i
            //如果队尾不满足单调性条件y[j1]-x[j1]>y[j2]-x[j2]，则丢弃
            //y[j1]是points[j][1], x[j1]是points[j][0]
            //y[j2]是points[i][1], x[j2]是points[i][0]
            //if(points[j][1] - points[j][0] <= points[i][1] - points[i][0]){
            while(!q.isEmpty() && points[q.getLast()][1] - points[q.getLast()][0] <= points[i][1] - points[i][0]){
                q.removeLast();
            }

            //当前的i加入队列尾
            q.add(i);
        }

        return ans;
    }
}
