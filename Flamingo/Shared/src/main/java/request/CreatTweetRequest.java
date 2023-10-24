package request;

import models.TweetDemo;
import response.Response;

public class CreatTweetRequest extends Request {
    private final TweetDemo tweetDemo;

    public CreatTweetRequest(TweetDemo tweetDemo) {
        this.tweetDemo = tweetDemo;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.creatTweet(tweetDemo);
    }
}
