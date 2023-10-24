package response;

import models.SignUpForm;

public class PersonalInfoResponse extends Response {
    SignUpForm form;
    public PersonalInfoResponse(SignUpForm form) {
        this.form = form;

    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.personalInfo(form);
    }
}
