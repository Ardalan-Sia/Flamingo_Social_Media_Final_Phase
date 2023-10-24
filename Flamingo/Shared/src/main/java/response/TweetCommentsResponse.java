package response;

import models.CommentDemo;

import java.util.LinkedList;

public class TweetCommentsResponse extends Response{
    private LinkedList<CommentDemo> comments;
    private int parentId;

    public TweetCommentsResponse(LinkedList<CommentDemo> comments , int parentId) {
        this.comments = comments;
        this.parentId = parentId;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.tweetComments(comments,parentId);
    }
}
