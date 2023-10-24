package response;

import models.UserDemo;

import java.util.LinkedList;

public class Followers extends Response{
    LinkedList<UserDemo> demos;

    public Followers(LinkedList<UserDemo> demos) {
        this.demos = demos;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {

    }
}
