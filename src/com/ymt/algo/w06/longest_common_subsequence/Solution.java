package com.ymt.algo.w06.longest_common_subsequence;

/**
 * 思路：
 * i表示text1中字符的位置
 * j表示text2中字符的位置
 *
 * 在某一时刻，text1和text2中的字符分别在i和j位置
 *
 * 下一个字符的决策存在三种情况：
 *
 * 从下往上：
 * 1. text1[i] == text2[j], f[i][j] = f[i + 1][j + 1] + 1
 * 2. text1[i] != text2[j]
 *     2.1 选择text1中的下一个字符，就是f[i + 1][j]
 *     2.2 选择text2中的下一个字符，就是f[i][j+1]
 * 此时，f[i][j] = max(f[i + 1][j], f[i][j + 1])
 *
 * 从上往下就是
 * text1[i] == text2[j], f[i][j] = f[i - 1][j - 1] + 1
 * text1[i] != text2[j], f[i][j] = max(f[i][j - 1], f[i - 1][j])
 *
 * @author yumingtao
 * @date 2021/7/30 23:47
 */
public class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        //采用从上往下的方式，会使用i-1和j-1, 所以让下标从1开始，需要给text1和text2前边补上“”
        text1 = " " + text1;
        text2 = " " + text2;

        int len1 = text1.length();
        int len2 = text2.length();

        int[][] f = new int[len1][len2];

        //对f进行初始化
        //" "字符串和任意字符串都没有公共子序列
        f[0][0] = 0;
        for(int j = 1; j < len2; j++){
            f[0][j] = 0;
        }

        for(int i = 1; i < len1; i++){
            f[i][0] = 0;
        }

        //循环所有的状态
        for(int i = 1; i < len1; i++){
            for(int j = 1; j < len2; j++){
                //枚举所有的决策
                if(text1.charAt(i) == text2.charAt(j)){
                    f[i][j] = f[i - 1][j - 1] + 1;
                }else{
                    f[i][j] = Math.max(f[i][j - 1], f[i - 1 ][j]);
                }
            }
        }

        return f[len1 - 1][len2 - 1];
    }

    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";

        Solution solution = new Solution();
        int longestLength = solution.longestCommonSubsequence(text1, text2);
        System.out.println(longestLength);
    }
}
