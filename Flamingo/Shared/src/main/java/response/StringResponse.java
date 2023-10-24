package response;

public class StringResponse extends Response{
    String message;
    public StringResponse(String message) {
        this.message = message;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.showMessage(message);
    }
}
