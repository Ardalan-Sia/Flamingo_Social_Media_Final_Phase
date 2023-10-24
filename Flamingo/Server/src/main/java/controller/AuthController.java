package controller;

import Tools.RegistrationCheck;
import db.Context;
import model.User;
import models.SignInForm;
import models.SignUpForm;
import models.UserStatues;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AuthController extends Controller {

    private String password = "";
    private String username = "";
    private String name = "";
    private String email = "";
    private String phoneNumber = "";
    private Date birthDay ;
    private String bio = "";
    private User owner;

    public AuthController() {

    }

    public User signUp(SignUpForm e) throws Exception {
        RegistrationCheck registrationCheck = new RegistrationCheck();
        name = registrationCheck.checkName(e.getName());
        username = registrationCheck.checkUsername(e.getUsername());
        email = registrationCheck.checkEmail(e.getEmail());
        password = registrationCheck.checkPassword(e.getPassword1(), e.getPassword2());
        phoneNumber = registrationCheck.checkPhoneNumber(e.getPhoneNumber());
        birthDay = e.getBirthDay();
        bio = e.getBio();
        User user = new User(e.getUsername(), password, name, email, phoneNumber, birthDay, bio);
        context.Users.add(user);
        logger.info("registered successfully");
        user.setStatues(UserStatues.ONLINE);
        owner = user;
        logger.info("@"+user.getUsername()+" has signed up");
        context.Users.update(user);
        return user;
    }

    public User signIn(SignInForm e) throws Exception {
        username = e.getUsername();
        password = e.getPassword();
        for (User user : context.Users.all()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                logger.info("loggedIn successfully");
                user.setStatues(UserStatues.ONLINE);
                owner = user;
                logger.info("@"+user.getUsername()+" has signed in");
                context.Users.update(user);
                return user;
            }
        }
            logger.warn("login failed");
            throw new Exception("login failed");
    }

    public void logout(){
        owner.setStatues(UserStatues.OFFLINE);
        updateCurrentUserLastSeen(owner);
        logger.info("@"+owner.getUsername()+" has logged out");
        context.Users.update(owner);
    }

    public static void updateCurrentUserLastSeen(User owner){
        if (owner != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" hh:mm");
            owner.setLastSeen(formatter.format(LocalDateTime.now()));
            Context context = new Context();
            context.Users.update(owner);
        }
    }

}
