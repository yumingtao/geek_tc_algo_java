package com.ymt.algo.w02.combinations;

import java.util.ArrayList;
import java.util.List;

/**
 * 主体思想：
 * 1...n个数，可以分为两种情况：1. 存在于组合中 2.存在于组合中
 *
 * @author yumingtao
 * @date 6/27/21 10:35 AM
 */
public class Solution {
    List<List<Integer>> result;
    List<Integer> set;
    int n;
    int k;

    public List<List<Integer>> combine(int n, int k) {
        result = new ArrayList<>();
        set = new ArrayList<>();
        this.n = n;
        this.k = k;
        find(1);

        return result;
    }

    private void find(int i) {
        //0.终止条件
        //0.1 当递归层次大于数字n的时候返回，注意此时需要判断set中数字是否满足k的要求
        /*if (i > n) {
            if (set.size() == k) {
                //这里注意要用copy
                result.add(new ArrayList<>(set));
            }
            return;
        }*/

        //0.2 当集合长度满足要求k时，结束
        if (set.size() == k) {
            //这里注意要用copy
            result.add(new ArrayList<>(set));
            return;
        }

        //当递归层次大于数字n的时候返回
        if (i > n) {
            return;
        }

        //如果存在于组合中
        set.add(i);
        //进入到下一层查找
        find(i + 1);
        //清理现场，注意和add成对出现
        set.remove(set.size() - 1);

        //如果不存在于组合中
        find(i + 1);
    }
}
