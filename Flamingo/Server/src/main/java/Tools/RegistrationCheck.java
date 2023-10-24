package Tools;


import controller.Controller;
import model.User;

public class RegistrationCheck extends Controller {


    public RegistrationCheck() {


    }

    public  String checkUsername(String username) throws Exception {
        if (username.isEmpty()) {
            logger.warn("auth problem duplicateUsername");
            throw new Exception("usernameRequired");
        }
        for (User user :context.Users.all()) {
            String username1 = "";
            if (user.getUsername().equals(username) && !user.getUsername().equals(username1)) {
                logger.warn("auth problem duplicateUsername");
                throw new Exception("duplicateUsername");
            }
        }
        return username;
    }

    public  String checkName(String name) throws Exception {
        if (name.isEmpty()) {
            logger.warn("auth problem nameRequired");
            throw new Exception("nameRequired");
        }
        return name;
    }

    public  String checkEmail(String email) throws Exception {
        if (email.isEmpty()) {
            logger.warn("auth problem nameRequired");
            throw new Exception("emailRequired");
        }
        for (User user :context.Users.all()) {
            String email1 = "";
            if (user.getEmail().equals(email) && !user.getEmail().equals(email1) ) {
                logger.warn("auth problem duplicateEmail");
                throw new Exception("duplicateEmail");
            }
        }
        return email;
    }

    public  String checkPhoneNumber(String phoneNumber) throws Exception {
        if (!context.Users.all().isEmpty())
        for (User user :context.Users.all()) {
            String phoneNumber1 = "";
            if (user.getPhoneNumber().equals(phoneNumber)
                    && !phoneNumber.isEmpty()
                    && !user.getPhoneNumber().equals(phoneNumber1)) {
                logger.warn("auth problem duplicatePhoneNumber");
                throw new Exception("duplicatePhoneNumber");
            }
        }
        return phoneNumber;
    }

    public  String checkPassword(String password1 , String password2) throws Exception {
        if (password1.isEmpty()) {
            logger.warn("auth problem password1Required");
            throw new Exception("password1Required");
        }        if (password2.isEmpty()) {
            logger.warn("auth problem password2Required");
            throw new Exception("password2Required");
        }        if (!password1.equals(password2)) {
            logger.warn("auth problem passwordConfirm");
            throw new Exception("passwordConfirm");
        }
        return password1;
    }
}
