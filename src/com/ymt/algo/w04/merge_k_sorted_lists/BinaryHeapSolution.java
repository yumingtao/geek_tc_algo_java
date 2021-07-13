package com.ymt.algo.w04.merge_k_sorted_lists;

/**
 * 思路：
 * 1. 对比合并两个链表的场景，使用双指针，那个链表元素的值小，把值放进去，然后指针+1，另一个链表指针不动
 * 2. 查找k个链表中最小元素，放进新链表中，然后指针+1，其余链表指针不动
 * 3. 每次都要从所有元素中取出一个最小值，可以使用小根堆
 * 4. 开发一个简单的二叉堆，存储链表元素
 *
 * @author yumingtao
 * @date 7/12/21 11:15 PM
 */
public class BinaryHeapSolution {
    private MinRootBinaryHeap binaryHeap = new MinRootBinaryHeap();

    public ListNode mergeKLists(ListNode[] lists) {
        //取出k个链表的头节点，放入到开发的小根二叉堆中
        for (ListNode node : lists) {
            if (node != null) {
                binaryHeap.add(node);
            }
        }

        //创建保护节点
        ListNode head = new ListNode();
        //创建尾节点，指向队尾
        ListNode tail = head;
        //当堆不为空时取出堆顶
        while (!binaryHeap.isEmpty()) {
            //取出堆顶，堆顶是val最小的元素
            ListNode node = binaryHeap.poll();

            tail.next = node;
            tail = node;

            //如果链表不为空，放入堆，继续比较最小
            if (node.next != null) {
                binaryHeap.add(node.next);
            }
        }

        return head.next;
    }

    public static void main(String[] args) {
        MinRootBinaryHeap heap = new MinRootBinaryHeap();
        ListNode node1 = new ListNode(-8);
        ListNode node2 = new ListNode(-2);
        ListNode node3 = new ListNode(-10);
        ListNode node4 = new ListNode(-10);
        ListNode node5 = new ListNode(-7);
        ListNode node6 = new ListNode(-7);
        ListNode node7 = new ListNode(-7);
        ListNode node8 = new ListNode(-5);

        heap.add(node1);
        heap.add(node2);
        heap.add(node3);
        heap.add(node4);
        heap.add(node5);
        heap.add(node6);
        heap.add(node7);
        heap.add(node8);

        System.out.println();
        ListNode node;
        while (!heap.isEmpty()) {
            node = heap.poll();
            System.out.println(node.val);
        }
        System.out.println();
    }
}

class MinRootBinaryHeap {

    private int size = 0;
    /**
     * 二叉堆是通过数组来存储元素，数组下标从0开始
     * 根据题0 <= k <= 10^4
     * 因为下标从0开始，最大子节点的下标为2*p+2
     * 所以这里暂时定义一个长度为2*10^4+2的数组
     */
    private final int capacity = 500 * 10000;
    ListNode[] nodes = new ListNode[capacity];

    /**
     * 判断队列元素是否为0
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回并删除堆顶元素
     * 删除堆顶元素步骤：
     * 1.将堆顶和堆尾元素交换，然后删除堆尾
     * 2.向下调整堆顶元素，调整步骤
     * 2.1 取出左右子节点最小的元素与堆顶比较
     * 2.2 如果堆顶元素小于等于左右子节点最小的元素，停止
     * 2.3 如果堆顶元素大于左右子节点最小的元素，交换，索引设置为左右子节点最小元素的索引，继续向下调整
     * <p>
     * 采用递归向下调整
     *
     * @return 堆顶元素
     */
    public ListNode poll() {
        ListNode node = nodes[0];

        //交换堆顶和堆尾，删除堆尾
        nodes[0] = nodes[size - 1];
        nodes[size - 1] = null;

        //向下递归调整新堆顶元素
        downInsertNode(0);
        size--;
        //返回堆顶元素
        return node;
    }

    private void downInsertNode(int index) {
        //终止条件，入股索引到了数组尾部
        if (index == size - 1) {
            return;
        }

        //主体逻辑
        int leftIndex = index * 2 + 1;
        int rightIndex = index * 2 + 2;

        if (nodes[leftIndex] == null && nodes[rightIndex] == null) {
            return;
        }

        int minIndex = leftIndex;
        //判断右子节点的情况
        if (nodes[leftIndex] == null || (nodes[rightIndex] != null && nodes[leftIndex] != null && nodes[leftIndex].val > nodes[rightIndex].val)) {
            minIndex = rightIndex;
        }

        if (nodes[index].val > nodes[minIndex].val) {
            ListNode tmpNode = nodes[index];
            nodes[index] = nodes[minIndex];
            nodes[minIndex] = tmpNode;

            //index从小节点的位置继续递归
            downInsertNode(minIndex);
        }
    }

    /**
     * 二叉堆要提供add方法，插入元素的步骤
     * 1.插入的元素向存放到数组的尾部
     * 2.向上调整
     * 2.1 如果到了堆顶（数组的开始元素），停止
     * 2.2 和父节点比较，父节点索引(p-1)/2，如果大于父节点的值，停止
     * 2.2 如果小于父节点的值，交换p与(p-1)/2，令p=(p-1)/2，依次向上调整
     * <p>
     * 采用递归实现向上调整
     */
    public void add(ListNode node) {
        //新添加的元素存放到数组的尾部
        nodes[size] = node;
        //递归向上调整
        upInsertNode(size);
        size++;
    }

    private void upInsertNode(int index) {
        //终止条件
        //达到了堆顶
        if (index == 0) {
            return;
        }

        //父节点为(index-1)/2
        //肯定有父节点，所以不用判断父节点是否为空
        int parentIndex = (index - 1) / 2;
        if (nodes[parentIndex].val > nodes[index].val) {
            ListNode tmpNode = nodes[parentIndex];
            nodes[parentIndex] = nodes[index];
            nodes[index] = tmpNode;

            //令index=(index-1)/2，继续递归
            upInsertNode(parentIndex);
        }
        //当节点的值>=父节点的值时，函数自然结束
    }
}
