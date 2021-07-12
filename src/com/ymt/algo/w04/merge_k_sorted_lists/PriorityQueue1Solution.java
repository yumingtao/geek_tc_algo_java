package com.ymt.algo.w04.merge_k_sorted_lists;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 思路：
 * 1. 暴力解法，使用优先队列
 * 2. 利用小根堆的性质，每次由优先队列维护最小值
 * <p>
 * 一共由kn个元素，时间复杂度O(knlogkn)
 *
 * @author yumingtao
 * @date 7/12/21 3:56 PM
 */
public class PriorityQueue1Solution {
    Queue<Integer> queue = new PriorityQueue<>();

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head;

        //循环k个链表
        for (int k = 0; k < lists.length; k++) {
            //循环每一个链表，将值放入优先队列
            head = lists[k];
            while (head != null) {
                queue.add(head.val);
                head = head.next;
            }
        }

        head = new ListNode();
        //设置tail节点，指向最后一个节点
        ListNode tail = head;
        while (!queue.isEmpty()) {
            int val = queue.poll();
            ListNode node = new ListNode(val);
            tail.next = node;
            tail = node;
        }

        return head.next;
    }
}
