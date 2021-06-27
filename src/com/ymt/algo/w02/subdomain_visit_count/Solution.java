package com.ymt.algo.w02.subdomain_visit_count;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 主体思想：
 * 1. 使用递归，逐级域名判断，通过"."，逐级截取域名
 * 2. 当到达顶级域名时结束
 * 3. 使用map保存域名出现的次数，整体计算结束后，拼装结果
 *
 * 备注：想复杂了，时间复杂度高O(数组长度*域名级数)，看了题解，可以逆序遍历数组，拼接字符串，时间复杂度O(n)
 *
 * @author yumingtao
 * @date 6/27/21 8:58 PM
 */
public class Solution {
    Map<String, Integer> subdomainCount = new HashMap<>();

    public List<String> subdomainVisits(String[] cpdomains) {
        List<String> result = new ArrayList<>();
        int count;
        String[] firstSplits;

        for (String s1 : cpdomains) {
            firstSplits = s1.split(" ");
            count = Integer.valueOf(firstSplits[0]);
            calculate(firstSplits[1], count);
        }

        for (String key : subdomainCount.keySet()) {
            result.add(String.format("%d %s", subdomainCount.get(key), key));
        }

        return result;
    }

    private void calculate(String domain, int count) {
        //终止条件
        if (!domain.contains(".")) {
            //计算顶级域名，然后返回
            countSubdomain(domain, count);
            return;
        }

        //处理逻辑
        countSubdomain(domain, count);

        //进入到下一层
        String subDomain = getSubdomain(domain);
        calculate(subDomain, count);

        //清理
    }

    private String getSubdomain(String domain) {
        int index = domain.indexOf(".");
        return domain.substring(index + 1, domain.length());
    }

    private void countSubdomain(String key, int value) {
        if (subdomainCount.containsKey(key)) {
            subdomainCount.put(key, subdomainCount.get(key) + value);
        } else {
            subdomainCount.put(key, value);
        }
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"9001 discuss.leetcode.com"};
        Solution solution = new Solution();
        List<String> result = solution.subdomainVisits(strs);

        System.out.println(">result:" + result);
    }
}
