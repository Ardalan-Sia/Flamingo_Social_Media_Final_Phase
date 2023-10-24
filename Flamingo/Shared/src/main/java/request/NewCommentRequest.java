package request;

import models.CommentDemo;
import response.Response;

public class NewCommentRequest extends Request {
    private CommentDemo demo;

    public NewCommentRequest(CommentDemo demo) {
        this.demo = demo;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.newComment(demo);
    }
}
