package response;

import models.LastSeenType;

public class PrivacyInfoResponse extends Response {

    private LastSeenType lastSeenType;
    private boolean isPrivate;
    private String password;

    public PrivacyInfoResponse(LastSeenType lastSeenType, boolean isPrivate , String password) {
        this.lastSeenType = lastSeenType;
        this.isPrivate = isPrivate;
        this.password = password;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.privacyInfo(lastSeenType,isPrivate,password);
    }
}
