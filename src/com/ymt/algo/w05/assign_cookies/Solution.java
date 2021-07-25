package com.ymt.algo.w05.assign_cookies;

import java.util.Arrays;

/**
 * 思路：
 * 1.大的饼干必然满足胃口小的孩子
 * 2.胃口小的孩子从最小的饼干开始吃，看是否满足
 * 3.大的饼干留给后边胃口大的孩子
 *
 * @author yumingtao
 * @date 2021/7/25 11:22
 */
public class Solution {
    public int findContentChildren(int[] g, int[] s) {
        //分别对g，s生序排序
        Arrays.sort(g);
        Arrays.sort(s);

        //大的饼干必然满足胃口小的孩子
        //胃口小的孩子从最小的饼干开始吃，看是否满足
        //大的饼干留给后边胃口大的孩子
        int ans = 0;
        int sCount = 0;
        for(int i = 0; i < g.length; i++){
            //饼干从上一次i吃掉的后一个位置开始
            while(sCount < s.length){
                if(g[i] <= s[sCount++]){
                    //饼干可以满足
                    ans++;
                    break;
                }
            }
        }

        return ans;
    }
}
