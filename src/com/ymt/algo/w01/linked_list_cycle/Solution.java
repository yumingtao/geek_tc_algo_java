package com.ymt.algo.w01.linked_list_cycle;

/**
 * 主体思路：使用快慢指针，相遇则说明有环
 * 可以参考现实中跑步压圈，跑得快的追上跑得慢的，压圈了，说明有环
 *
 * @author yumingtao
 * @date 6/21/21 10:14 PM
 */
public class Solution {
    public boolean hasCycle(ListNode head) {

        //排除空链表和一个节点链表的情况
        if (head == null || head.next == null) {
            return false;
        }

        //快慢指针赋值
        ListNode fast = head.next.next;
        ListNode slow = head;

        //有环的时候，fast后边一定还有节点，所以需要判断fast.next != null
        while (fast != null && fast.next != null && slow != null) {
            if (fast.val == slow.val) {
                return true;
            }

            //注意整个程序的终止条件，当最快或最慢的走到最后也没有找到环
            //fast.next在外层循环判断过，这里可以不用判断
            if (slow.next == null || fast.next.next == null) {
                break;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
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
