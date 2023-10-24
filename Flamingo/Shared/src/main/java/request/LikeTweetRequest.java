package request;

import response.Response;

public class LikeTweetRequest extends Request{
    private int tweetId;
    private boolean liked;

    public LikeTweetRequest(int tweetId , boolean liked) {
        this.tweetId = tweetId;
        this.liked = liked;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.likeTweet(tweetId , liked);
    }
}
