package com.ymt.algo.w01.two_sum_ii_input_array_is_sorted;

/**
 * @author yumingtao
 * @date 2021/7/19 20:50
 */
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        /**
         * 数组是单调递增，使用双指针i,j
         * nums[i] + nums[j] = target,当i递增时，j时递减的
         */
        int j = numbers.length - 1;
        for (int i = 0; i < numbers.length; i++) {
            /**
             * 固定i，当j在递减的过程中，没有找到j满足条件
             * while循环终止于i==j或是numbers[i] + numbers[j] <= target
             * 当numbers[i] + numbers[j] < target时，如果还满足i<j
             * 此时继续j--也没有意义，所以终止
             */
            while (i < j && numbers[i] + numbers[j] > target) {
                j--;
            }
            /**
             * while终止的一个条件是numbers[i] + numbers[j] = target
             * 判断i,j是否满足条件，因为元素不能重复使用，所以需要满足i<j
             */
            if (i < j && numbers[i] + numbers[j] == target) {
                return new int[]{i + 1, j + 1};
            }

            //继续递增i，看是否有满足条件的
        }
        return new int[]{};

        /**
         * 整体的时间复杂度
         * 因为while的终止条件是i=j,当i递增时，j从上一次的位置递减
         * 当i在走一遍的过程总，j只走一遍，所以是O(n)
         * 暴力i，j循环的时间复杂度是O(n2)
         */
    }
}
