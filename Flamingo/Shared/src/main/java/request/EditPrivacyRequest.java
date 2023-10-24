package request;

import models.LastSeenType;
import response.Response;

public class EditPrivacyRequest extends Request {
    private LastSeenType lastSeenType;
    private boolean isPrivate;
    private String password;

    public EditPrivacyRequest(LastSeenType lastSeenType, boolean isPrivate, String password) {
        this.lastSeenType = lastSeenType;
        this.isPrivate = isPrivate;
        this.password = password;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.editPrivacySettings(isPrivate, lastSeenType,password);
    }
}
