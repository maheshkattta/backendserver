package com.example.loginutil.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users")
public class User {


    // Generate getters and setters for 'id'
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // Generate getters and setters for 'username'
    @Column(name = "username") // Column mapping
    private String username;

    @Column(name = "phone-number") // Column mapping
    private String phonenumber;
    // Generate getters and setters for 'password'
    @Column(name = "password") // Column mapping
    private String password;


    public User(){

    }
    public User(Long id,String username,String password,String phonenumber) {
        this.id = id;
        this.password=password;
        this.username =username;
        this.phonenumber=phonenumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setphonenumber(String phonenumber){
        this.phonenumber = phonenumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Other fields, methods, and constructors
}



