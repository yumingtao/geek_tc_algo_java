package com.ymt.algo.w01.design_circular_deque;

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 * <p>
 * 采用双向链表方式实现
 * 需要定义Node辅助，head和tail，需要考虑head和tail重合的情况
 * 注意处理边界的时候，insertXX，deleteXX要考虑全各种边界情况
 *
 * @author yumingtao
 * @date 6/21/21 10:07 AM
 */
public class MyCircularDeque {

    //容量
    int capacity;

    //当前大小
    int size;

    //头节点
    Node head;

    //尾节点
    Node tail;

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public MyCircularDeque(int k) {
        this.capacity = k;
        size = 0;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (size < capacity) {
            if (size == 0) {
                head = new Node(value);
                tail = head;
            } else {
                Node tempNode = new Node(value);
                tempNode.next = head;
                head.pre = tempNode;
                head = tempNode;
            }
            size++;
            return true;
        }

        return false;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (size < capacity) {
            if (size == 0) {
                tail = new Node(value);
                head = tail;
            } else {
                Node tempNode = new Node(value);
                tempNode.pre = tail;
                tail.next = tempNode;
                tail = tempNode;
            }
            size++;
            return true;
        }

        return false;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (size == 0) {
            return false;
        }

        //清空
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
        }
        size--;
        return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (size == 0) {
            return false;
        }

        if (size == 1) {
            //清空
            tail = null;
            head = null;
        } else {
            tail = tail.pre;
        }

        size--;
        return true;
    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        if (head == null || size == 0) {
            return -1;
        }

        return head.val;
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        if (tail == null || size == 0) {
            return -1;
        }

        return tail.val;
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        return size == capacity;
    }

    class Node {
        int val;
        Node pre;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        //"MyCircularDeque","insertFront","getRear","deleteLast","getRear",
        // "insertFront","insertFront","insertFront","insertFront","isFull",
        // "insertFront","isFull","getRear"
        //[[77],[89],[],[],[],[19],[23],[23],[82],[],[45],[],[],[],[],[],[74],[],[],[98],[],[99]
        MyCircularDeque q = new MyCircularDeque(77);
        q.insertFront(89);
        System.out.println(q.getRear());
        System.out.println(q.deleteLast());
        System.out.println(q.getRear());
        q.insertFront(19);
        q.insertFront(23);
        q.insertFront(82);
        System.out.println(q.isFull());
        q.insertFront(45);
        System.out.println(q.isFull());
        System.out.println(q.getRear());
    }
}
