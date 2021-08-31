package com.ymt.algo.test02.grouping_tower;

import java.util.Scanner;

/**
 * @author yumingtao
 * @date 2021/8/29 16:51
 */
public class Main {
    private final static int LEN = 5001;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //先读取铁塔的数量n
        int n = scanner.nextInt();

        long[] sums = new long[LEN];
        //读取每座铁塔能能提供钢材的量,注意数组下标从1开始计算前缀和
        long h;
        for (int i = 1; i <= n; i++) {
            h = scanner.nextLong();
            //计算铁塔高度的前缀和
            sums[i] = sums[i - 1] + h;
        }


        //用f[i]表示到第i个铁塔最多分为多少组，f数组单调不降，用s[i]数组表示前缀和,g[i]表示以第i个塔为最后一组高度和
        //
        //那么当s[i]-s[j]>=g[j]时f[i]=f[j]+1,因为f单调不降，所以分组的塔的高度和满足时越小越好。

        //动态规划数组表示第i个分多少组，默认值都是0
        long[] dp = new long[LEN];
        //dp[0] = 0; //不合法的值，因为默认是0，可以不设置

        //区间高度和，表示当以第i个铁塔结尾分组时，这一组铁塔的高度之和
        long[] heightSums = new long[LEN];

        //循环所有的状态
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                //由区间段和公式sums[i]-sums[j+1-1] -> sums[i]-sums[j]
                //表示以j+1下标开始的铁塔(因为j已经被分到上一个分组)，到以i下标结束的铁塔形成一组的话的高度和是,记作l
                //heightSums[j]表示，以j下标结尾的分组，即最后一个最优分组的铁塔高多和
                //因为分组要求，分组的高度和单调不递减
                //所以如果当前的l>=heightSums[j]，说明满足最优子结构，还存在最优解，设置f[i]=f[j]+1
                //然后将以i下标结尾的分组的铁塔高度和赋值给heightSums[i]
                long l = sums[i] - sums[j];
                if (l >= heightSums[j]) {
                    dp[i] = dp[j] + 1;
                    heightSums[i] = l;
                }
            }
        }

        System.out.println(n - dp[n]);
    }
}