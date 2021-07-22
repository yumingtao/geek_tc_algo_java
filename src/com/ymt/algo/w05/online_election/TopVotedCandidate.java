package com.ymt.algo.w05.online_election;

import java.util.*;

/**
 * 思路:
 * 1. 统计每一个下标i的时刻，领先的候选人，并存入链表中
 * 2. times是严格递增的数组，给定一个时刻t，二分查找<=t的最大时刻的下标index
 * 3. 返回index对应得到领先的候选人
 *
 * @author yumingtao
 * @date 2021/7/21 20:21
 */
public class TopVotedCandidate {
    //记录i对应的时刻时的总票数领先的候选人
    List<LeadingCandidate> leadingCandidates = new ArrayList<>();

    public TopVotedCandidate(int[] persons, int[] times) {
        //保存每个候选人的总票数
        Map<Integer, Integer> countMapping = new HashMap<>();
        //先计算某一个时刻，领先的人是谁
        //得到最大索引，到persons中统计每个person的选票计数

        //当前时刻，领先的候选人
        int currentLeadingCandidate = 0;
        //当前时刻领先的候选人的总票数
        int currentLeaderVoteCount = 0;

        for (int i = 0; i < persons.length; i++) {
            int currentPerson = persons[i];
            int count = countMapping.getOrDefault(currentPerson, 0) + 1;
            countMapping.put(currentPerson, count);

            if (count >= currentLeaderVoteCount) {
                //i时刻候选人的总票数大于当前时刻候选人的总票数
                //注意使用=，表示票数相等时，选择最后一次投票的候选人
                //如果i时刻的候选人不是当前领先的候选人，更改领先的候选人为当前的候选人
                if (currentPerson != currentLeadingCandidate) {
                    currentLeadingCandidate = currentPerson;
                }
                //更新当前候选人的票数
                currentLeaderVoteCount = count;
            }

            //添加times[i]时刻领先的候选人
            leadingCandidates.add(new LeadingCandidate(currentLeadingCandidate, times[i]));
        }
    }

    public int q(int t) {
        //首先在找到<=t时刻的最大时间下标
        //使用二分查找
        //这里需要注意模板中的下标范围[-1, n-1]
        int left = -1;
        int right = leadingCandidates.size() - 1;

        //O(logn)
        while (left < right) {
            int mid = left + (right - left + 1) / 2;

            if (leadingCandidates.get(mid).time <= t) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return leadingCandidates.get(right).person;
    }

    /**
     * 领先的候选人
     */
    class LeadingCandidate {
        public int person;
        public int time;

        public LeadingCandidate() {
        }

        public LeadingCandidate(int person, int time) {
            this.person = person;
            this.time = time;
        }
    }

    public static void main(String[] args) {
        int[] persons = new int[]{0, 0, 0, 0, 1};
        int[] times = new int[]{0, 6, 39, 52, 75};

        TopVotedCandidate topVotedCandidate = new TopVotedCandidate(persons, times);
        int person = topVotedCandidate.q(99);
        System.out.println(person);
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */

// 示例：
//
// 输入：["TopVotedCandidate","q","q","q","q","q","q"], [[[0,1,1,0,0,1,0],[0,5,10,1
//5,20,25,30]],[3],[12],[25],[15],[24],[8]]
//输出：[null,0,1,1,0,0,1]
//解释：
//时间为 3，票数分布情况是 [0]，编号为 0 的候选人领先。
//时间为 12，票数分布情况是 [0,1,1]，编号为 1 的候选人领先。
//时间为 25，票数分布情况是 [0,1,1,0,0,1]，编号为 1 的候选人领先（因为最近的投票结果是平局）。
//在时间 15、24 和 8 处继续执行 3 个查询。
//["TopVotedCandidate","q","q","q","q","q","q","q","q","q","q"]
//[[[0,0,0,0,1],[0,6,39,52,75]],[45],[49],[59],[68],[42],[37],[99],[26],[78],[43]]