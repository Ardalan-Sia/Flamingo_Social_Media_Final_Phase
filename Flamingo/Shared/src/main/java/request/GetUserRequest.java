package request;

import response.Response;

public class GetUserRequest extends Request{
    private final String username;

    public GetUserRequest(String username) {
        this.username = username;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.getUser(username);
    }
}
