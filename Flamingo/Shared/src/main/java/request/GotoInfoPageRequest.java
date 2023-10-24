package request;

import response.Response;

public class GotoInfoPageRequest extends Request {
    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.goToInfoPage();
    }
}
