package com.ymt.algo.lession01.move_zeros;

/**
 * 同样的思想
 * 考虑什么时候数据要保留下来，元素不为0的时候
 * 这类问题一般都需要两个指针
 * @author yumingao
 * @date 6/18/21 3:59 PM
 */
public class Solution {
    public void moveZeroes(int[] nums) {
        if(nums.length == 0){
            return;
        }

        int n = 0;

        //遍历数组，如果元素不为零，责保留
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0){
                nums[n++] = nums[i];
            }
        }

        //不为零的数据，通过慢指针都已经移动到了左边
        //需要将剩余部分的数据都置为0
        //注意n已经+1了
        for(int j = n; j < nums.length; j++){
            nums[j] = 0;
        }
    }
}
