package response;

import models.SentRequestStatue;

import java.util.LinkedHashMap;

public class SentRequests extends Response{
    LinkedHashMap<String , SentRequestStatue> sentRequests;

    public SentRequests(LinkedHashMap<String, SentRequestStatue> sentRequests) {
        this.sentRequests = sentRequests;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.sentNotifications(sentRequests);
    }
}
