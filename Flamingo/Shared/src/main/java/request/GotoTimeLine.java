package request;

import response.Response;

public class GotoTimeLine extends Request {
    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.gotoTimeLine();
    }
}
