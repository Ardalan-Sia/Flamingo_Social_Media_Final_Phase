package response;

import models.UserDemo;

import java.util.LinkedList;

public class Followings extends Response{
    LinkedList<UserDemo> demos;

    public Followings(LinkedList<UserDemo> demos) {
        this.demos = demos;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {

    }
}
