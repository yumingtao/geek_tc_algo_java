package com.ymt.algo.w02.permutations;

import java.util.ArrayList;
import java.util.List;

/**
 * 主体思想：
 *    考虑每0...n-1的每个位置放什么数
 *
 * @author yumingtao
 * @date 6/27/21 11:50 AM
 */
public class Solution {
    List<List<Integer>> result;
    List<Integer> set;
    boolean[] numUsed;
    int[] nums;

    public List<List<Integer>> permute(int[] nums) {
        result = new ArrayList<>();
        set = new ArrayList<>();
        this.nums = nums;

        //使用数组记录当前数是否被使用过
        numUsed = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numUsed[i] = false;
        }

        find(0);

        return result;
    }

    /**
     * 使用index，控制当前位置
     * @param index
     */
    public void find(int index) {

        //终止条件
        if (index == nums.length) {
            //存放结果
            result.add(new ArrayList<>(set));
            return;
        }

        //处理逻辑
        for (int i = 0; i < nums.length; i++) {
            //如果数字没有被用过
            if (!numUsed[i]) {
                //使用这个数字，填入当前位置
                set.add(nums[i]);

                //标记该数字已经用过
                numUsed[i] = true;

                //进入到index的下一层，即进入到一个位置继续判断
                find(index + 1);

                //清理现场
                numUsed[i] = false;
                set.remove(set.size() - 1);
            }
        }
    }
}
