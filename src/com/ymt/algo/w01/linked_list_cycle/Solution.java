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

        if (head == null || head.next == null) {
            return false;
        }

        ListNode fast = head.next.next;
        ListNode slow = head;

        while (fast != null && slow != null) {
            //有环的时候，fast后边一定还有节点
            if ((fast.val == slow.val) && fast.next != null) {
                return true;
            }

            if (slow.next != null && fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }else{
                //注意整个程序的终止条件，当最快或最慢的走到最后也没有找到环
                break;
            }
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
