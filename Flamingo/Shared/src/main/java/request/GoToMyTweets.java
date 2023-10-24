package request;

import response.Response;

public class GoToMyTweets extends Request {
    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.goToMyTweets();
    }
}
