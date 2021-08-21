package com.ymt.algo.w08.implement_strstr;

/**
 * 使用Robin-Karp Hash算法
 *
 * @author yumingtao
 * @date 2021/8/21 18:05
 */
public class Solution2 {
    //定义一个较大质数
    int p = (int) 1e9 + 7;
    public int strStr(String haystack, String needle) {
        int nLen = needle.length();
        //特例判断
        if (nLen == 0) return 0;

        int hLen = haystack.length();

        //预处理
        //0.因为前缀hash从1开始，通常这里把字符串变成从1开始，使用H[r]-H[l-1], 否则要使用H[r+1]-H[l]
        String s = " " + haystack;
        String t = " " + needle;

        //1.计算needle的hash,从1开始到nLen,同时计算b的(r-l+1)次幂，这里r-l+1就是nLen
        long tHashVal = 0;
        long p131 = 1;
        for (int i = 1; i <= nLen; i++) {
            tHashVal = (tHashVal * 131 + t.charAt(i) - 'a' + 1) % p;
            //注意此处要模p，否则越界
            p131 = p131 * 131 % p;
        }

        //2.计算haystack的前缀hash，从1开始到hLen
        long[] sHash = new long[hLen + 1];
        sHash[0] = 0;
        for (int i = 1; i <= hLen; i++) {
            sHash[i] = (sHash[i - 1] * 131 + s.charAt(i) - 'a' + 1) % p;
        }

        //3.滑动窗口，判读haystack中的子串hash是否与needle的hash相等
        //注意i表示滑动窗的尾部索引
        for (int i = nLen; i <= hLen; i++) {
            //计算前缀字串的hash, 前缀字串从i-nLen+1开始，到nLen
            //注意java中要模p之后要+p再模p，确保模数落到1...p-1范围
            int lIndex = i - nLen + 1;
            long subHash = ((sHash[i] - sHash[lIndex - 1] * p131) % p + p) % p;

            //如果字串的hash值与目标串的hash值相等
            if (subHash == tHashVal) {
                //hash值相等，串不一定相等，一般情况下都会相等，此处double check
                //注意在原字符串中开始索引要-1
                //if(s.substring(lIndex, i + 1).equals(t.substring(1))){
                if (haystack.substring(lIndex - 1, i).equals(needle)) {
                    //注意前边处理时，将字符串变成下标从1开始，所以返回结果是需要-1
                    return lIndex - 1;
                }
            }
        }
        return -1;
    }
}
