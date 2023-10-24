package response;

import models.SystemNotification;
import request.Request;

import java.util.LinkedHashSet;

public class SystemRequestResponse extends Response {
    private LinkedHashSet<SystemNotification> systemNotifications;

    public SystemRequestResponse(LinkedHashSet<SystemNotification> systemNotifications) {
        this.systemNotifications = systemNotifications;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.systemNotifications(systemNotifications);
    }
}
