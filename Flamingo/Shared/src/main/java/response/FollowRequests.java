package response;

import models.UserDemo;

import java.util.LinkedList;

public class FollowRequests extends Response{
    LinkedList<UserDemo> demos;

    public FollowRequests(LinkedList<UserDemo> demos) {
        this.demos = demos;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.requests(demos);
    }
}
