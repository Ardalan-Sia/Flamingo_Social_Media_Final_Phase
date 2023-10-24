package response;

public class EditInfoResponse extends Response {
    private boolean success;
    private String  message;

    public EditInfoResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.editInfo(success,message);
    }
}
