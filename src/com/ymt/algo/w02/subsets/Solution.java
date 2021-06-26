package com.ymt.algo.w02.subsets;

import java.util.ArrayList;
import java.util.List;

/**
 * 主体思路：
 * 数组中的元素存在两种可能：1.出现在子集中 2.不出现在子集中
 * 相当于数组中每个元素，进行上边两种可能的判断，属于重叠子问题，考虑使用递归方法实现
 *
 * @author yumingtao
 * @date 6/26/21 10:25 PM
 */
public class Solution {
    //使用全局变量用于保存结果
    List<List<Integer>> result;
    //注意sub在每层递归过程中会改变
    List<Integer> sub;

    public List<List<Integer>> subsets(int[] nums) {
        result = new ArrayList<>();
        sub = new ArrayList<>();
        findSubset(nums, 0);

        return result;
    }

    private void findSubset(int[] nums, int index) {
        //0.递归终止条件
        if (index == nums.length) {
            //将本次递归结果保存
            //注意没次要保存sub的copy，否则对象的值会被更改
            result.add(new ArrayList<>(sub));
            return;
        }
        //1.如果存在
        //1.1添加到集合中
        sub.add(nums[index]);
        //1.2进入到下一级
        findSubset(nums, index + 1);

        //2.注意清理现
        sub.remove(sub.size() - 1);

        //3.如果不存在
        //3.1不添加到集合中
        //3.2进入到下一级
        findSubset(nums, index + 1);
    }
}
