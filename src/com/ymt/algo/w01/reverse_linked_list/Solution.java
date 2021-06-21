package com.ymt.algo.w01.reverse_linked_list;

/**
 * @author yumingtao
 * @date 6/18/21 5:12 PM
 */
public class Solution {
    public ListNode reverseList(ListNode head) {

        //主体思想：当前节点的next指向上一个节点
        ListNode last = null;

        //需要一个临时节点保存下一个节点
        ListNode nextHead = null;
        while (head != null) {
            nextHead = head.next;
            head.next = last;
            last = head;
            head = nextHead;
        }

        return last;
    }
}
