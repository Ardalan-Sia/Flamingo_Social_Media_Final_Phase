package response;

public abstract class LoginResponse extends Response {
    protected final boolean success;
    protected final String message;
    public LoginResponse(boolean success, String message ) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
