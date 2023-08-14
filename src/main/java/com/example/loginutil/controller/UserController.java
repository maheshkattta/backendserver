package com.example.loginutil.controller;

import com.example.loginutil.GlobalExceptionHandler;
import com.example.loginutil.Service.UserService;
import com.example.loginutil.entity.Apiresponse;
import com.example.loginutil.entity.User;
import com.example.loginutil.entity.UserCredentials;
import com.example.loginutil.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
@RestController
@RequestMapping("api/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GlobalExceptionHandler exceptionHandler;
    @PostMapping("/signup")
    public Apiresponse saveUser(@RequestBody User user_param) throws DataIntegrityViolationException {

        Apiresponse myresponse = new Apiresponse();


            User user = userService.saveUser(user_param);
            myresponse.setStatus(HttpStatus.valueOf(200));
            myresponse.setMessage("successfully created ");
            myresponse.setUser(user);
            return myresponse;

    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();

    }

    @PostMapping("/test")
    public  String  test(@RequestParam String test){
        return "tested successfully"+test;
    }
    @PostMapping("/login")
    public Apiresponse logincall(@RequestBody UserCredentials userCredentials) {
        // Retrieve the user by the provided phone number

        String username = userCredentials.getUsername();

        String password = userCredentials.getPassword();

        User user = userRepository.findByusername(username);

        Apiresponse myresponse = new Apiresponse();



        if (user != null) {
            if(Objects.equals(user.getPassword(), password)){

                myresponse.setMessage("success");
                myresponse.setUser(user);
                myresponse.setStatus(HttpStatus.valueOf(200));

                return  myresponse;


            }else{
                myresponse.setMessage("incorrect password");
                myresponse.setStatus(HttpStatus.valueOf(200));

                return myresponse;
            }
        } else {

            myresponse.setMessage("user not found");
            myresponse.setStatus(HttpStatus.valueOf(200));
            return myresponse;

        }
    }
    @GetMapping("user/{id}")
    public Apiresponse getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);

        Apiresponse myresponse = new Apiresponse();

        if (user != null) {
            myresponse.setUser(user);
            myresponse.setStatus(HttpStatus.valueOf(200));
            return myresponse;
        } else {
            myresponse.setStatus(HttpStatus.valueOf(200));
            return myresponse;

        }
    }

    @PostMapping("update-phone")
    public Apiresponse Update_phone(@RequestParam String username ,String phonenumber ) {
        User user = userRepository.findByusername(username);


        Apiresponse myresponse = new Apiresponse();

        if (user != null) {
            user.setphonenumber(phonenumber);

            userService.saveUser(user);
            myresponse.setUser(user);
            myresponse.setStatus(HttpStatus.valueOf(200));
            return myresponse;
        } else {
            myresponse.setMessage("unable to update");
            myresponse.setStatus(HttpStatus.valueOf(200));
            return myresponse;

        }

    }

    @PostMapping("delete-user")
    public Apiresponse delete_user(@RequestParam String username){

        Apiresponse myresponse = new Apiresponse();

        User user = userRepository.findByusername(username);
        if(user!=null) {
            Long id = user.getId();
            userService.DeleteUser(id);
            myresponse.setMessage("deleted successfully");
            myresponse.setStatus(HttpStatus.valueOf(200));
            return myresponse;
        }else{
            myresponse.setMessage("no user found");
            myresponse.setStatus(HttpStatus.valueOf(200));
            return myresponse;
        }

    }
    @PostMapping("update-password")
    public Apiresponse Update_password(@RequestParam String username ,String oldpassword,String newpassword ) {
        User user = userRepository.findByusername(username);


        Apiresponse myresponse = new Apiresponse();

        if (user != null) {

            if(Objects.equals(oldpassword, user.getPassword())){
            user.setPassword(newpassword);

            userService.saveUser(user);
            myresponse.setUser(user);
            myresponse.setMessage("password successfully changed");
            myresponse.setStatus(HttpStatus.valueOf(200));
            return myresponse;
            }
            myresponse.setMessage("invalid old password");
            myresponse.setStatus(HttpStatus.valueOf(200));
            return myresponse;
        } else {

            myresponse.setMessage(username);
            myresponse.setStatus(HttpStatus.valueOf(200));
            return myresponse;

        }

    }
}