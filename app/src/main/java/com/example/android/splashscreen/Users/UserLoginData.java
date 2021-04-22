package com.example.android.splashscreen.Users;

public class UserLoginData {
    private static String fullname , username , email , phone ;
    private static String ID;

    public UserLoginData() {
    }

    public UserLoginData(String fullname, String username, String email, String phone, String ID) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.ID = ID;
    }

    public static String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public static String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
