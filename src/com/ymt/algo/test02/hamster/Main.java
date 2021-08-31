package com.ymt.algo.test02.hamster;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author yumingtao
 * @date 2021/8/29 15:38
 */
public class Main {
    static class MousePos {
        int x;
        int y;
        int count;

        public MousePos(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //先读取第一行的N和M
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        //接下来的N行是出现地鼠的坐标, 并统计每个坐标，地鼠出现的次数
        int[][] mousePos = new int[n][2];
        Map<String, Integer> mousePosCount = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            mousePos[i][0] = x;
            mousePos[i][1] = y;
            String key = x + "_" + y;
            mousePosCount.put(key, mousePosCount.getOrDefault(key, 0) + 1);
        }

        //接下来是M行植物的坐标，并通过set记录下每个植物的位置
        Set<String> plantSet = new HashSet<>();
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            String key = x + "_" + y;
            if (!plantSet.contains(key)) {
                plantSet.add(key);
            }
        }

        //对地鼠出现的位置进行排序，找到可以出手的位置
        TreeSet<MousePos> set = new TreeSet<>((o1, o2) -> {
            if (o1.count == o2.count) {
                if (o1.x + o1.y == o2.x + o2.y) return o2.x - o1.x;
                return o2.x + o2.y - (o1.x + o1.y);
            }

            return o1.count - o2.count;
        });

        for (int i = 0; i < n; i++) {
            int x = mousePos[i][0];
            int y = mousePos[i][1];
            String key = x + "_" + y;
            int count = mousePosCount.getOrDefault(key, 0);
            //如果位置不是植物，才可以出手, 将该位置存入TreeSet，并有TreeSet维护升序
            if (!plantSet.contains(key)) {
                set.add(new MousePos(x, y, count));
            }
        }

        //获取能够打的最有可能出现地鼠的位置
        if (set.size() > 0) {
            //如set有值，找到按照排序规则最大的一个，注意java中TreeSet默认是按照从小到大排序
            //所以取最后一个为最大
            MousePos pos = set.last();
            System.out.print(pos.x + " " + pos.y);
        } else {
            //否则输出空
            System.out.println("");
        }
    }
}