package com.example.recipe.bean;

import java.util.List;

public class User {
    private String userName;
    private String userPassword;
    private String userEmail;
    private List<Integer> userCollect;


    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<Integer> getUserCollect() {
        return userCollect;
    }

    public void setUserCollect(List<Integer> userCollect) {
        this.userCollect = userCollect;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
