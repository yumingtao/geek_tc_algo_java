package com.ymt.algo.w05.sort_an_array;

/**
 * @author yumingtao
 * @date 2021/7/22 23:04
 */
public class QuickSort {
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int pivot = partition(nums, l, r);
        quickSort(nums, l, pivot);
        quickSort(nums, pivot + 1, r);
    }

    private int partition(int[] nums, int l, int r) {
        //随机选择一个中轴元素索引
        int pivot = l + (int) (Math.random() * (r - l + 1));
        //设置中轴元素的值
        int pivotVal = nums[pivot];

        while (l <= r) {
            //当中轴元素左边元素的值小于中轴元素的值时，l++
            while (nums[l] < pivotVal) {
                l++;
            }

            //当中轴元素右边元素的值大于中轴元素的值时，r--
            while (nums[r] > pivotVal) {
                r--;
            }

            if (l == r) {
                break;
            }

            //当中轴元素左边元素的值大于中轴元素的值，中轴元素右边元素的值小于中轴元素的值时，交换nums[l]和nums[r]
            if (l < r) {
                int temp = nums[r];
                nums[r] = nums[l];
                nums[l] = temp;
                l++;
                r--;
            }
        }

        return r;
    }
}
