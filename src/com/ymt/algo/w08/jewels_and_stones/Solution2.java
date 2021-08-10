package com.ymt.algo.w08.jewels_and_stones;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yumingtao
 * @date 2021/8/10 12:16
 */
public class Solution2 {
    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> jSet = new HashSet<>();
        for (int i = 0; i < jewels.length(); i++) {
            jSet.add(jewels.charAt(i));
        }

        int ans = 0;
        for (int i = 0; i < stones.length(); i++) {
            if (jSet.contains(stones.charAt(i))) {
                ans++;
            }
        }

        return ans;
    }
}
