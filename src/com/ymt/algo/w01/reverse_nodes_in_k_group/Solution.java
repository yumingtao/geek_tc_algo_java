package com.ymt.algo.w01.reverse_nodes_in_k_group;

/**
 * 采用分治思想，每一组内做链表翻转
 * 注意每一组内，两端边界节点具有上下相关性
 *
 * @author yumingtao
 * @date 6/22/21 5:34 PM
 */
public class Solution {

    public static void main(String[] args) {
        //1,2,3,4,5
        ListNode node = new ListNode(1);
        ListNode head = node;
        for (int i = 2; i <= 5; i++) {
            node.next = new ListNode(i);
            node = node.next;
        }
        Solution solution = new Solution();
        ListNode ans = solution.reverseKGroup(head, 2);
        System.out.println();
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        //保护节点，指向head
        ListNode protect = new ListNode(0, head);

        //上一组的尾
        ListNode lastTail = protect;

        //先找到第一组的开始和tail，开始就是head，计算tail
        ListNode tail = getTail(head, k);

        //下一组的head
        ListNode nextGHead;

        //什么时候结束
        //当没有tail的时候结束
        while (tail != null) {
            //下一组的head
            nextGHead = tail.next;

            //做组内反转 1(head)->2(tail) >> 2(新head，旧tail)->1(新tail，旧head)
            //head变成了新的tail，旧的tail的变成了新的head
            reverse(head, tail);

            //处理边界
            //上一组的tail指向这一组的新head
            //lastTail有可能null,需要处理，通过保护节点
            lastTail.next = tail;

            //这一组的新tail，指向下一组的head
            //本来下一组的head应该是旧tail.next，但是tail在组内被反转了，指向了组内的它原来的上一个节点
            //所以需要提前维护下一组的head
            head.next = nextGHead;

            //上一组的tail移动到新的tail(旧head)
            lastTail = head;
            //开始节点移动到下一组开始
            head = nextGHead;
            tail = getTail(head, k);
        }

        //反转之后，第一组的tail成为了链表的新head
        return protect.next;
    }

    private ListNode getTail(ListNode head, int k) {
        while (head != null) {
            k--;

            //k==0,说明剩余的节点还够，可以找到tail
            if (k == 0) {
                return head;
            }
            head = head.next;
        }

        //循环结束，没有达到k==0，说明剩余节点不够，返回null
        return null;
    }

    /**
     * 1->2  >> 2->1
     *
     * @param head
     * @param tail
     */
    private void reverse(ListNode head, ListNode tail) {
        //当有一个节点时，直接返回
        if (head == tail) {
            return;
        }

        ListNode last = head;
        head = head.next;
        ListNode next;
        //当前节点指向上一个节点
        while (head != tail) {
            next = head.next;
            head.next = last;
            last = head;

            //head向后移动，完成循环
            head = next;
        }

        //主要解决只有两个元素，导致链表没有反转
        tail.next = last;
    }
}
