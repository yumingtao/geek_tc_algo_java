package com.ymt.algo.w04.design_twitter;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yumingtao
 * @date 7/14/21 1:20 PM
 */
public class TwitterTest {
    /**
     * Your Twitter object will be instantiated and called as such:
     */
    public static void main(String[] args) throws InterruptedException {
        TwitterTest tt = new TwitterTest();
        tt.test1();
        tt.test2();
        tt.test3();
    }

    public void test1() throws InterruptedException {
        Twitter obj = new Twitter();
        obj.postTweet(2, 1);
        TimeUnit.SECONDS.sleep(1);

        obj.postTweet(1, 1);
        TimeUnit.SECONDS.sleep(1);

        obj.postTweet(2, 2);
        TimeUnit.SECONDS.sleep(1);

        obj.postTweet(2, 3);
        List<Integer> feeds1 = obj.getNewsFeed(1);
        System.out.println("feeds1:" + feeds1.toString());

        List<Integer> feeds2 = obj.getNewsFeed(2);
        System.out.println("feeds2:" + feeds2.toString());

        obj.follow(1, 2);
        List<Integer> feeds3 = obj.getNewsFeed(1);
        System.out.println("feeds3:" + feeds3.toString());

        obj.unfollow(1, 2);
        List<Integer> feeds4 = obj.getNewsFeed(1);
        System.out.println("feeds4:" + feeds4.toString());
    }

    /**
     * ["Twitter","postTweet","getNewsFeed","follow","getNewsFeed","unfollow","getNewsFeed"]
     * [[],[1,1],[1],[2,1],[2],[2,1],[2]]
     *
     * [null,null,[1],null,[1],null,[]]
     */

    public void test2() {
        Twitter obj = new Twitter();
        obj.postTweet(1, 1);

        List<Integer> feeds1 = obj.getNewsFeed(1);
        System.out.println("feeds1:" + feeds1.toString());

        obj.follow(2, 1);
        List<Integer> feeds2 = obj.getNewsFeed(2);
        System.out.println("feeds2:" + feeds2.toString());

        obj.unfollow(2, 1);
        List<Integer> feeds3 = obj.getNewsFeed(2);
        System.out.println("feeds3:" + feeds3.toString());
    }

    /**
     * ["Twitter","postTweet","postTweet","unfollow","follow","getNewsFeed"]
     * [[],[1,4],[2,5],[1,2],[1,2],[1]]
     */
    public void test3() {
        Twitter obj = new Twitter();
        obj.postTweet(1, 4);
        obj.postTweet(2, 5);

        obj.unfollow(1,2);
        obj.follow(1,2);

        List<Integer> feeds1 = obj.getNewsFeed(1);
        System.out.println("feeds1:" + feeds1.toString());
    }

}
