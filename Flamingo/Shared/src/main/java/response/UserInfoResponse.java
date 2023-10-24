package response;

import models.UserDemo;

public class UserInfoResponse extends Response {
    private UserDemo userDemo;

    public UserInfoResponse(UserDemo userDemo) {
        this.userDemo = userDemo;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.userInfo(userDemo);
    }
}
