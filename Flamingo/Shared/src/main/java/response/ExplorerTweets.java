package response;

import models.TweetDemo;

import java.util.LinkedList;

public class ExplorerTweets extends Response{
    LinkedList<TweetDemo> list;

    public ExplorerTweets(LinkedList<TweetDemo> list) {
        this.list = list;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.explorerTweets(list);
    }
}
