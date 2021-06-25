package com.ymt.algo.w02.lru_cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 最近最少使用
 * 主体思想：
 * 使用map+双向链表
 * map存储key和节点
 * 双向链表维护顺序，最近使用的数据放到链表头部，尾部是最老使用节点
 * 插入数据时
 * 1. 当key不存在
 * 1.1 判断节点容量，如果达到容量，删除最老节点，即链表尾部
 * 1.2 在链表头部插入节点
 * 2. 当key存在，更行value，删除链表中节点，在链表头部插入节点
 * 获取数据时
 * 1. key不存在，直接返回-1
 * 2. key存在，删除链表中节点，在链表头部插入节点，来模拟最近使用，然后返回value
 * 为什么要使用双向链表：
 * map的value存放是的节点，如果map中存在key-value，该节点可能在链表中任意位置，需要删除它，再放入链表头
 * 使用双向链表不需要从头遍历链表，通过前后指针可以快速插入和删除
 * 如果使用单链表，则需要从头遍历才能找到对应的节点
 * <p>
 * 使用链表时，注意建立保护节点
 * 单向链表建立一个保护节点
 * 双向链表建立两个，并要建立关联
 *
 * @author yumingtao
 * @date 6/25/21 9:58 AM
 */
public class LRUCache {

    Map<Integer, Node> map;
    int capacity;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.map = new HashMap<>(capacity);
        this.capacity = capacity;
        this.head = new Node();
        this.tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            //如果存在，删除节点，在链表头插入节点，来模拟最近使用过
            Node node = map.get(key);
            //删除节点
            removeNodeFromList(node);
            //在列表头插入节点
            insertNodeToListHead(node);
            return node.value;
        }

        //如果不存在，直接返回
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            //删除节点
            removeNodeFromList(node);
            //因为存在，不删除，更新即可
            //map.remove(key);

            //在列表头插入节点
            insertNodeToListHead(node);

            //更新value
            node.value = value;
            map.put(key, node);

            return;
        }

        //如果不存在，
        //先判断容量，容量满，删除最近最少使用的
        if (map.size() == capacity) {
            removeLRU();
        }

        //创建node，并放入链表头，后存入map
        addNewCache(key, value);
    }

    private void addNewCache(int key, int value) {
        Node node = new Node();
        node.key = key;
        node.value = value;
        insertNodeToListHead(node);
        map.put(key, node);
    }

    /**
     * 将tail的上一个节点删除，tail的上一个节点时tail.pre
     */
    private void removeLRU() {
        //在map中删除key-value
        map.remove(tail.pre.key);
        //链表末尾删除节点
        tail.pre.pre.next = tail;
        tail.pre = tail.pre.pre;
    }

    /**
     * 在保护节点head和head之后节点插入node
     */
    private void insertNodeToListHead(Node node) {
        //head之后的节点是head.next，建立node和head之后节点的关联
        head.next.pre = node;
        node.next = head.next;

        //建立node和head节点的关联
        node.pre = head;
        head.next = node;
    }

    private void removeNodeFromList(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    class Node {
        public int key;
        public int value;
        public Node pre;
        public Node next;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */