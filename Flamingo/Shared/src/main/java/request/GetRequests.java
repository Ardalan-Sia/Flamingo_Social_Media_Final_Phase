package request;

import response.Response;

public class GetRequests extends Request {
    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.getRequests();
    }
}
