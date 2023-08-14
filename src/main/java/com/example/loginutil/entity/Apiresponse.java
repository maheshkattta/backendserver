package com.example.loginutil.entity;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class Apiresponse {

    public HttpStatus status;
    public String message;

    public User user;

    public  Apiresponse(){

    }
    public Apiresponse(HttpStatus status, String message, User user) {
        this.user = user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public void setStatus(HttpStatus status) {
        this.status = status;
    }


    public void setMessage(String message) {
        this.message = message;
    }

}
