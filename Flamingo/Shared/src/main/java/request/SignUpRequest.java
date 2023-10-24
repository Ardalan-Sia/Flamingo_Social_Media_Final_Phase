package request;

import models.SignUpForm;
import response.Response;

public class SignUpRequest extends Request{

    private SignUpForm formEvent;

    public SignUpRequest(SignUpForm signUpForm) {
        this.formEvent = signUpForm;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.signUp(formEvent);
    }
}
