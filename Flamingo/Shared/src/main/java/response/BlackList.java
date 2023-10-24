package response;

import models.UserDemo;

import java.util.LinkedList;

public class BlackList extends Response{
    LinkedList<UserDemo> demos;

    public BlackList(LinkedList<UserDemo> demos) {
        this.demos = demos;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {

    }
}
