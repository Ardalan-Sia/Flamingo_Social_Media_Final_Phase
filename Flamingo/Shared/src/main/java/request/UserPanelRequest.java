package request;

import response.Response;

public class UserPanelRequest extends Request{
    private String request;
    private int userId;

    public UserPanelRequest(String request, int userId) {
        this.request = request;
        this.userId = userId;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.userPanelActionRequest(userId,request);
    }
}
