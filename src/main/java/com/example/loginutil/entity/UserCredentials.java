package com.example.loginutil.entity;

public class UserCredentials {


    private String password;

    private String username;

    public UserCredentials(){

    }

    public UserCredentials(String username,String password) {
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    // Getters and setters
}
