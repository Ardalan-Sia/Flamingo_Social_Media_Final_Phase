package request;

import response.Response;

public class FollowAnswerRequest extends Request{
    boolean accepted;
    int userId;

    public FollowAnswerRequest(boolean accepted, int userId) {
        this.accepted = accepted;
        this.userId = userId;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.followAnswer(userId,accepted);
    }
}
