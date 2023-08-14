package com.example.loginutil.Service;


import com.example.loginutil.entity.User;
import com.example.loginutil.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
        @Autowired
        private UserRepository userRepository;


        public List<User> getAllUsers() {
                return userRepository.findAll();
        }
        public  void DeleteUser(Long id){
                userRepository.deleteById(id);
        }



        public User saveUser(User user) {return userRepository.save(user);
        }

        public User getUserById(Long id) {
                return userRepository.findById(id).orElse(null);
        }
}

