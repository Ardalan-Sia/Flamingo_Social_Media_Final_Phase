package request;

import response.Response;

public class GetComments extends Request{

    private int tweetId;

    public GetComments(int tweetId) {
        this.tweetId = tweetId;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.getComments(tweetId);
    }
}
