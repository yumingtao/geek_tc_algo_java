package com.ymt.algo.w06.triangle;

import java.util.List;

/**
 * 思路：
 * 1.从最后一层向上遍历，更新上一层的元素
 * 2.注意上一层j的取值范围是当前取值范围-1
 *
 * @author yumingtao
 * @date 2021/7/28 10:12
 */
public class Solution1 {
    public int minimumTotal(List<List<Integer>> triangle) {
        //从最后一层开始向上遍历
        for (int i = triangle.size() - 1; i >= 1; i--) {
            //当前层所有值列表
            List<Integer> cur = triangle.get(i);

            //上一层所有值列表
            List<Integer> pre = triangle.get(i - 1);

            //遍历上一层中所有的元素
            for (int j = 0; j < cur.size() - 1; j++) {
                //当前层中相邻两个元素最小值 + 上一层元素的值，做为上一层的最小值
                int val = Math.min(cur.get(j), cur.get(j + 1)) + pre.get(j);
                //边界
                if (i >= 1) {
                    triangle.get(i - 1).set(j, val);
                }
            }
        }

        return triangle.get(0).get(0);
    }
}
