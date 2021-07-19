package com.ymt.algo.w01.two_sum;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 思路：
 * 1.先对数组进行排序
 * 2.套用two-sum-ii-input-array-is-sorted模板
 *
 * @author yumingtao
 * @date 2021/7/19 22:15
 */
public class Solution2 {
    public int[] twoSum(int[] nums, int target) {
        //直接排序，下标乱了
        //Arrays.sort(nums);
        Item[] items = new Item[nums.length];
        for(int i = 0; i < nums.length; i++){
            Item item = new Item(nums[i], i);
            items[i] = item;
        }

        Arrays.sort(items, new Comparator<Item>(){
            @Override
            public int compare(Item o1, Item o2){
                if(o1.val > o2.val){
                    return 1;
                }else if(o1.val < o2.val){
                    return -1;
                }
                return 0;
            }
        });

        int j = nums.length - 1;

        for(int i = 0; i < j; i++) {
            while(i < j && items[i].val + items[j].val > target){
                j--;
            }
            if(i < j && items[i].val + items[j].val == target) {
                return new int[]{items[i].index, items[j].index};
            }
        }

        return new int[]{};
    }

    class Item{
        public int val;
        public int index;

        public Item(){}

        public Item(int val, int index){
            this.val = val;
            this.index = index;
        }
    }
}
