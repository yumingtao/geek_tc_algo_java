package com.ymt.algo.w03.course_schedule_ii;

import java.util.*;

/**
 * 思路：
 * 课程表问题的变体
 * 1.根据课程前置条件，可以构建出一张图，遍历图，找到没有前置课程的课开始学习，如果可以找到的数量和总课程数量相同，则可以学完
 * 2.可以使用入度数组表示一门课有前置课程，有一门前置课程，入度+1，学完一门前置课程，入度值-1，入度值为0，表示该课程可学
 * <p>
 * 步骤：
 * 1.根据课程前置条件数组，构建每一个课程的出边数组和每一个课程的入度数组
 * 2.遍历图，当到达一个点的时候，它的入度值减1，如果减为0，可学课程数+1, 同时可学课程数组加入该课程
 * 3.比较可学课程数是否等于课程总数，相等返回可学课程数组，否则返回空数组
 *
 * @author yumingtao
 * @date 7/5/21 9:01 AM
 */
public class Solution {
    private List<List<Integer>> outEdges = new ArrayList<>();
    private int[] inDegrees;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //初始化入度数组，默认入度都是0
        inDegrees = new int[numCourses];

        //初始化出边数组
        for (int i = 0; i < numCourses; i++) {
            outEdges.add(new ArrayList<>());
        }

        //根据前置条件数组，构造出边数组并计算课程入度数
        for (int[] pre : prerequisites) {
            //pre[1]是pre[0]的前置必修课程，所以添加有pre[1]->pre[0]的有向边
            int x = pre[1];
            int y = pre[0];
            addOutEdge(x, y);
            inDegrees[y]++;
        }

        //采用BFS遍历图
        //每次从入度数组中选择入度为0的课程，加入到可学队列
        Queue<Integer> learnableCourses = new LinkedList<>();
        //记录可学课程的数组
        int[] learnedCourses = new int[numCourses];
        int index = 0;
        //记录可学课程的数量
        int learnedCount = 0;

        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                learnedCount++;
                learnedCourses[index++] = i;
                learnableCourses.add(i);
            }
        }

        //模板，BFS遍历图，遍历一个点的出边数组
        while (!learnableCourses.isEmpty()) {
            int course = learnableCourses.poll();

            //循环该课程的出边，将出边端点课程的入度-1
            for (int y : outEdges.get(course)) {
                inDegrees[y]--;
                //如果一个课程的入度为0，加入可学课程队列
                if (inDegrees[y] == 0) {
                    learnedCount++;
                    learnedCourses[index++] = y;
                    learnableCourses.add(y);
                }
            }
        }

        if (learnedCount == numCourses) {
            return learnedCourses;
        } else {
            return null;
        }
    }

    /**
     * 添加边的模板
     */
    private void addOutEdge(int x, int y) {
        outEdges.get(x).add(y);
    }
}
