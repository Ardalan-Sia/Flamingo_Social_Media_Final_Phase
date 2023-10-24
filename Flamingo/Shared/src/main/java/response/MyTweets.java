package response;

import models.TweetDemo;

import java.util.LinkedList;

public class MyTweets extends Response {

    private LinkedList<TweetDemo> tweetList;

    public MyTweets(LinkedList<TweetDemo> tweetList) {
        this.tweetList = tweetList;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.myTweetsList(tweetList);
    }
}
