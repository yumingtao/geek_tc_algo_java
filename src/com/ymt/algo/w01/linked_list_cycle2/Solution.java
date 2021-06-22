package com.ymt.algo.w01.linked_list_cycle2;

/**
 * 主题思想：
 *    head到环入口点长度l，环入口点到相遇点长度p，环的长队是r
 *           l             p
 *    head---------入口----------相遇点------｜
 *                  ｜---------------------｜
 *                            r-p
 *    快指针每次走2步，慢指针每次走1步
 *    如果第一次相遇，2倍慢指针都过的步数和快指针走过的步数相等
 *    一定有这样的公式 2(l+p) = l+nr+p -> l+p=nr -> l = nr-p = (n-1)r + r-p
 *    l = r-p + (n-1)r -> 慢指针从head和相遇点开始，最终会在环入口点第一次相遇
 *
 * @author yumingtao
 * @date 6/22/21 8:51 AM
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        //当空或只有一个节点，肯定无环
        if (head == null || head.next == null) {
            return null;
        }

        //注意，慢指针走1步，快指针走2步
        ListNode fast = head.next.next;
        ListNode slow = head.next;
        ListNode meet = null;

        //当快指针到了终点还没有相遇，肯定就不会相遇了
        while (fast != null && fast.next!= null){
            if(fast.val == slow.val){
                meet = slow;
                break;
            }

            fast = fast.next.next;
            slow = slow.next;
        }

        // meet!=null,说明一定有环
        if(meet == null){
            return null;
        }

        //有环则不需要考虑null情况,且循环肯定会返回
        //慢指针分别从head和meet开始，第一次相遇的节点为结果
        while (meet.val != head.val){
            meet = meet.next;
            head = head.next;
        }

        return head;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}

