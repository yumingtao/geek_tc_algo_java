package com.ymt.algo.lession01.merge_two_sorted_lists;

/**
 * @author yumingtao
 * @date 6/18/21 5:56 PM
 */
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //类比合并两个有
        ListNode protect = new ListNode();
        ListNode newHead = new ListNode();
        protect = newHead;

        //循环两个链表
        //当l1较小，放入新链表，l1继续往后走，l2位置不动
        //当l2较小，放入新链表，l2继续往后走，l1位置不动
        //注意边界，到了表尾就要停止
        while (l1 != null && l2 != null){
            if(l1.val <= l2.val){
                newHead.next = l1;
                l1 = l1.next;
                newHead = newHead.next;
            }else{
                newHead.next = l2;
                l2 = l2.next;
                newHead = newHead.next;
            }
        }

        if(l2 == null){
            while (l2== null && l1 != null){
                newHead.next = l1;
                l1 = l1.next;
                newHead = newHead.next;
            }
        }

        if(l1 == null){
            while (l1==null && l2 != null){
                newHead.next = l2;
                l2 = l2.next;
                newHead = newHead.next;
            }
        }

        return protect.next;
    }
}
