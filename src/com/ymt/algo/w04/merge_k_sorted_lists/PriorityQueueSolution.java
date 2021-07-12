package com.ymt.algo.w04.merge_k_sorted_lists;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 思路：
 * 1. 对比合并两个链表的场景，使用双指针，那个链表元素的值小，把值放进去，然后指针+1，另一个链表指针不动
 * 2. 查找k个链表中最小元素，放进新链表中，然后指针+1，其余链表指针不动
 * 3. 每次都要从所有元素中取出一个最小值，可以使用小根堆
 * 4. 使用优先队列，优先队列是二叉堆最简单的应用
 * 5. 注意将ListNode放入优先队列中，要实现Comparator接口
 * <p>
 * 一共由kn个元素，每次比较的是k个元素，时间复杂度O(knlogk)
 *
 * @author yumingtao
 * @date 7/12/21 3:56 PM
 */
public class PriorityQueueSolution {
    Queue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
        @Override
        public int compare(ListNode node1, ListNode node2) {
            if (node1.val < node2.val) {
                return -1;
            } else if (node1.val > node2.val) {
                return 1;
            }
            return 0;
        }
    });

    public ListNode mergeKLists(ListNode[] lists) {
        //循环k个链表，将每个链表的头放入到队列中
        //根据优先队列的特性，最小的node会在堆顶
        for (ListNode node : lists) {
            //判断链表不为空
            if(node != null){
                queue.add(node);
            }
        }

        //设置保护节点
        ListNode head = new ListNode();
        //设置tail节点，指向最后一个节点
        ListNode tail = head;
        while (!queue.isEmpty()) {
            //取出堆顶
            ListNode node = queue.poll();
            tail.next = node;
            tail = node;

            //如果取出堆顶的链表还有节点
            // 将node的下一个节点填入到优先队列继续比较
            if (node.next != null) {
                queue.add(node.next);
            }
        }

        return head.next;
    }
}

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
