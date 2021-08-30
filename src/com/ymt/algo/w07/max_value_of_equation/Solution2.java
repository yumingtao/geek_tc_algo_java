package com.ymt.algo.w07.max_value_of_equation;

/**
 * @author yumingtao
 * @date 2021/8/28 22:03
 */
public class Solution2 {
    public int findMaxValueOfEquation(int[][] points, int k) {
        int ans = Integer.MIN_VALUE;
        int left = 0;
        int right = 0;

        //双指针，当right小于points长度时
        while (right < points.length) {
            //1. 维护left和right的位置
            //如果right <= left，right需要向右移动，扩大窗口范围
            if (right <= left) {
                right++;
                //通过continue循环来判断，right++后是否还满足条件
                continue;
            }

            //根据xj-xi<=k，如果 x[right]-x[left]>k，说明left和right离的太远了，left需要先右移动
            if (points[right][0] - points[left][0] > k) {
                left++;
                //通过continue循环来判断，right++后是否还满足条件
                continue;
            }

            //2.维护yi + yj + xj - xi的最大值
            ans = Math.max(ans, points[left][1] + points[right][1] + points[right][0] - points[left][0]);

            //如果right没有到达终点，那么在right后边可能还存在一个新的right点，right2
            //将right2与left带入公式yi + yj + xj - xi，记作value1 = y[left]+y[right2]+x[right2]-x[left]
            //将right2与right带入公式yi + yj + xj - xi，记作value2 = y[right]+y[right2]+x[right2]-x[right]
            //如果value2>value1,则说明后边存在满足条件的更大的值，那么left继续向右移动
            //从value2>value1可以推导如下
            //y[right]+y[right2]+x[right2]-x[right] > y[left]+y[right2]+x[right2]-x[left] => y[right]-x[right] > y[left]-x[left]
            if (points[right][1] - points[right][0] > points[left][1] - points[left][0]) {
                left++;
                //通过continue循环来判断，right++后是否还满足条件
                continue;
            }

            //当right走到最后一个point时，left继续向右走，判断是否还有更大的值
            if (right == points.length - 1 && left < right) {
                left++;
                //通过continue，控制left继续向右移动
                continue;
            }

            right++;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] points = {{1, 3}, {2, 0}, {5, 10}, {6, -10}};

        Solution2 solution = new Solution2();
        int ans = solution.findMaxValueOfEquation(points, 1);
        System.out.println("ans:" + ans);
    }
}
