package com.example.demo.services;

import com.example.demo.Dtos.UserDto;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    public User getCurrentUser() {
        String username = myUserDetailsService.getCurrentUser();
        return userRepo.findByUsername(username);
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public User registerUser(UserDto user) {
        if (userRepo.existsByUsername(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        var newUser = myUserDetailsService.addUser(user.getUsername(), user.getEmail(), user.getPassword(), user.getRoles());

        var currentUser = this.getCurrentUser();

        if(currentUser == null || !currentUser.getRoles().contains("ADMIN") || newUser.getRoles() == null || newUser.getRoles().isBlank()){
            newUser.setRoles("USER");
        }
        return userRepo.save(newUser);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
