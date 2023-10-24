package response;

import models.TweetDemo;

import java.util.LinkedList;

public class TimeLineTweets extends Response{

    private LinkedList<TweetDemo> list;

    public TimeLineTweets(LinkedList<TweetDemo> list) {
        this.list = list;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.timeLineTweets(list);
    }
}
