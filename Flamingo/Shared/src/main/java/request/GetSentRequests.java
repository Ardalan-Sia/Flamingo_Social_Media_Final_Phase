package request;

import response.Response;

public class GetSentRequests extends Request{
    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.getSentRequests();
    }
}
