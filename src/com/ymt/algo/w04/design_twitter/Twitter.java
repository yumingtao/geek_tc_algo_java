package com.ymt.algo.w04.design_twitter;

import java.util.*;

/**
 * 思路：
 * 1.自己发的推文按照时间顺序由近及远存储
 * 1.1 自定义链表，每次发布推文时，插入到链表头部，每次取出队头
 * 2.关注者，使用Map存储，方便通过key查询和删除元素
 * 3.相当于每次从n个被关注对象和自己中分别取出一个元素，然后判断那个时间最近
 * 4.可以类比合并k个升序链表的思路，此处直接使用sdk内置的优先队列，需要自己实现比较器
 *
 * @author yumingtao
 * @date 7/13/21 8:52 PM
 */
public class Twitter {
    /**
     * 使用Map存储一个用户关注的用户
     */
    private Map<Integer, Set<Integer>> userFollowees;

    /**
     * 使用自定义链表存储推文
     * 使用Map存储每个用户的推文
     * key为用户id，value为该用户发送的推文链表
     */
    private Map<Integer, ListTweetNode> userTweets;

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        userFollowees = new HashMap<>();
        userTweets = new HashMap<>();
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        //创建一个推文及推文节点
        //注意此处使用nanoTime，防止测试时发送推文太快，造成大量时间戳相等的情况
        //使得优先队列顺序不定，和leetcode结果不一致
        Tweet tweet = new Tweet(tweetId, System.nanoTime(), userId);
        ListTweetNode newListTweetNode = new ListTweetNode(tweet);

        //判断用户是否发过推文
        if (userTweets.containsKey(userId)) {
            //用户发过推文
            ListTweetNode head = userTweets.get(userId);
            //将新的推文插入到头节点
            newListTweetNode.next = head;
        }
        userTweets.put(userId, newListTweetNode);

    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or
     * by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        List<ListTweetNode> lists = new ArrayList<>();

        //首先判断自己是否发了推文
        //使用下边的方式，让用户默认关注了自己，所以此处省略
        /*ListTweetNode ownTweetNodeHead = userTweets.get(userId);
        if (ownTweetNodeHead != null) {
            lists.add(ownTweetNodeHead);
        }*/

        //判断用户是否关注自己，如果没有，添加关注
        Set<Integer> followees = userFollowees.getOrDefault(userId, new HashSet<>());
        if (!followees.contains(userId)) {
            followees.add(userId);
            userFollowees.put(userId, followees);
        }

        //获取关注用户的推文链表
        for (Integer id : followees) {
            //通过用户id获取该用户发的推文链表
            ListTweetNode head = userTweets.get(id);

            //如果推文链表不为null，加入到中
            if (head != null) {
                lists.add(head);
            }
        }

        //使用优先队列处理
        Queue<ListTweetNode> queue = new PriorityQueue<>(new TweetComparator());
        for (ListTweetNode node : lists) {
            queue.add(node);
        }

        List<Integer> newsFeeds = new ArrayList<>();
        while (!queue.isEmpty() && newsFeeds.size() < 10) {
            ListTweetNode tweetNode = queue.poll();
            newsFeeds.add(tweetNode.tweet.getId());

            if (tweetNode.next != null) {
                queue.add(tweetNode.next);
            }
        }

        return newsFeeds;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        Set<Integer> followees = userFollowees.getOrDefault(followerId, new HashSet<>());
        followees.add(followeeId);
        userFollowees.put(followerId, followees);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        //先判断用户是否有关注其它用户
        if (userFollowees.containsKey(followerId)) {
            //如果有关注用户，取出关注列表，直接remove
            Set<Integer> followees = userFollowees.get(followerId);
            followees.remove(followeeId);
        }
    }
}

class ListTweetNode {
    public Tweet tweet;
    public ListTweetNode next;

    public ListTweetNode() {
    }

    public ListTweetNode(Tweet tweet) {
        this.tweet = tweet;
    }
}

/**
 * 注意按照时间由近及远排列
 * 所以当o1的时间戳大于o2的时间戳时返回-1，而不是1
 */
class TweetComparator implements Comparator<ListTweetNode> {
    @Override
    public int compare(ListTweetNode o1, ListTweetNode o2) {
        if (o1.tweet.getTs() > o2.tweet.getTs()) {
            return -1;
        } else if (o1.tweet.getTs() < o2.tweet.getTs()) {
            return 1;
        }
        return 0;
    }
}

class Tweet {
    /**
     * 推文id
     */
    private int id;

    /**
     * 推文发布的时间戳
     * 为了方便比较，直接记录ms
     */
    private long ts;

    /**
     * 用户id
     */
    private int userId;

    public Tweet(int id, long ts, int userId) {
        this.id = id;
        this.ts = ts;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public long getTs() {
        return ts;
    }

    public int getUserId() {
        return userId;
    }
}
