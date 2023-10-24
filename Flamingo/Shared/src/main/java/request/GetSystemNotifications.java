package request;

import response.Response;

public class GetSystemNotifications extends Request {
    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.getSystemNotifications();
    }
}
