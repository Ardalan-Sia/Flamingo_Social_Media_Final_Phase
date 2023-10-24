package response;

public class LogoutResponse extends Response{
    public LogoutResponse() {
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.logout();
    }
}
