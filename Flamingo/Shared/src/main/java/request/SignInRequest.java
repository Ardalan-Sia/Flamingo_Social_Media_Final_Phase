package request;

import models.SignInForm;
import response.Response;

public class SignInRequest extends Request{

    private SignInForm signInForm;

    public SignInRequest(SignInForm signInForm) {
        this.signInForm = signInForm;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.signIn(signInForm);
    }
}
