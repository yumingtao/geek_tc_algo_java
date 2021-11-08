package com.ymt.algo.w01.container_with_most_water;

/**
 * 思路:
 * 1.采用双指针夹逼 - 对撞指针
 * 2.当某一个指针指向的元素已经是最短，便不会再用，指针继续向前移动
 *
 * @author yumingtao
 * @date 2021/11/8 09:42
 */
public class Solution1 {
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int ans = 0;
        while(i < j){
            ans = Math.max(ans, Math.min(height[i], height[j]) * (j - i));
            if(height[i] < height[j]){
                //i已经是最短的，j不断向左移动，由于i，j距离拉近，不可能得到更大的容积
                //所以i向右移动
                i++;
            }else{
                //同理，j已经是最短的，i不断向右移动，由于i，j距离拉近，不可能得到更大的容积
                //所以j向左移动
                j--;
            }
        }

        return ans;
    }
}
