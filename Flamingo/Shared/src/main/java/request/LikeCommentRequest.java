package request;

import response.Response;

public class LikeCommentRequest extends Request{
    int parentId;
    int commentId;
    boolean liked;

    public LikeCommentRequest(int parentId, int commentId, boolean liked) {
        this.parentId = parentId;
        this.commentId = commentId;
        this.liked = liked;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.likeComment(parentId,commentId,liked);
    }
}
