package request;

import response.Response;

public class LogoutRequest extends Request{
    boolean exited;

    public LogoutRequest(boolean exited) {
        this.exited = exited;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.logout(exited);
    }
}
