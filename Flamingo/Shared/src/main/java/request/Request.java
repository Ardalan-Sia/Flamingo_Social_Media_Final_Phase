package request;

import response.Response;

public abstract class Request {
    private String authToken;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public abstract Response visit(RequestVisitor requestVisitor);
}
