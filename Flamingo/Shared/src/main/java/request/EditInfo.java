package request;

import models.SignUpForm;
import response.Response;

public class EditInfo extends Request {


    private SignUpForm form;

    public EditInfo(SignUpForm form) {
        this.form = form;
    }

    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.editInfoPage(form);
    }
}
