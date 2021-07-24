package com.ymt.algo.w05.kth_largest_element_in_an_array;

/**
 * 思路:
 * 1. 最简单的做法是，对数组进行排序，然后去第n-k小的数即为答案，但不是最优解，时间复杂度是O(nlogn)
 * 2. 利用快速排序选择中轴元素的特性可以将数组按照中轴元素p二分，左边都是<p，右边都是>p
 * 3. 判断n-k与p的关系，从而来决定是继续在p的左侧查找还是在p的右侧查找
 * 4. 时间复杂度可以达到O(n)
 *
 * @author yumingtao
 * @date 2021/7/24 16:58
 */
public class Solution {
    public int findKthLargest(int[] nums, int k) {
        /*Arrays.sort(nums);
        return nums[nums.length - k];*/
        return quickSelect(nums, nums.length - k, 0, nums.length - 1);
    }

    private int quickSelect(int[] nums, int k, int left, int right) {
        if (left >= right) {
            //说明已经排好序了，直接返回第k（其实是nums.length-k）个小的元素即可
            return nums[k];
        }

        int p = partition(nums, left, right);
        //当有重复元素的时候，k==p并不能说明已经排好序了，所以直接返回结果不对
        /*if (k == p) {
            return nums[p];
        }*/
        if (k <= p) {
            //在p的左侧继续查找
            return quickSelect(nums, k, left, p);
        } else {
            //在p的右侧继续查找
            return quickSelect(nums, k, p + 1, right);
        }
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = left + (int) (Math.random() * (right - left + 1));
        int pivotVal = nums[pivot];

        while (left <= right) {
            //当左侧的值小于中轴元素的值时，left++
            while (nums[left] < pivotVal) {
                left++;
            }

            //当右侧的值大于中轴元素的值时，right--
            while (nums[right] > pivotVal) {
                right--;
            }

            if (left == right) {
                break;
            }

            //否则交换nums[left]和nums[right]
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }

        return right;
    }
}
