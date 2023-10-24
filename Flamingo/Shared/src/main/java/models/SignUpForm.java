package models;

import java.util.Date;

public class SignUpForm  {
    private String name;
    private String username;
    private String email;
    private String password1;
    private String password2;
    private Date birthDay;
    private String bio;
    private String phoneNumber;
    private String encodedImage;

    public SignUpForm(String name, String username, String email, String password1, String password2, Date birthDay, String phoneNumber, String bio, String encodedImage) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
        this.encodedImage = encodedImage;
    }

    public String getName() {
        return name;
    }

    public String getUsername(){
        return username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getBio() {
        return bio;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }

}