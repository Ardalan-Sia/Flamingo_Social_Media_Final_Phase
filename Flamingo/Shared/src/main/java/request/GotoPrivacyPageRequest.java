package request;

import response.Response;

public class GotoPrivacyPageRequest extends Request {
    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.goToPrivacyPage();
    }
}
