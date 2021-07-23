package com.ymt.algo.w05.sort_an_array;

/**
 * @author yumingtao
 * @date 2021/7/23 15:40
 */
public class MergeSort {
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int left, int right) {
        //终止条件
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        //处理左半段
        mergeSort(nums, left, mid);
        //处理右半段
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    private void merge(int[] nums, int left, int mid, int right) {
        //临时中间数组
        int[] temp = new int[right - left + 1];
        //左半段索引
        int i = left;
        //右半段索引
        int j = mid + 1;
        //temp数组索引
        int k = 0;

        //合并两个有序数组
        //i>mid（左半段遍历完）或 j>right（右半段遍历完）时终止
        while (i <= mid && j <= right) {
            //将值较小的赋值给temp，然后移动索引
            temp[k++] = nums[i] <= nums[j] ? nums[i++] : nums[j++];
        }

        //如果左半段还没遍历完
        while (i <= mid) {
            temp[k++] = nums[i++];
        }

        //如果右半段还没遍历完
        while (j <= right) {
            temp[k++] = nums[j++];
        }

        //将临时数组copy到原数组
        for (int p = 0; p < temp.length; p++) {
            nums[left + p] = temp[p];
        }
    }
}
