package com.example.demo.controllers;

import com.example.demo.Dtos.UserDto;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String username) {
        var users = userService.getAllUsers(username);
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody @Validated UserDto user) {
        var newUser = userService.registerUser(user);
        var uri = URI.create("/api/v1/users/" + newUser.getUser_id());
        return ResponseEntity.created(uri).body(newUser);
    }

    @DeleteMapping("/{user_id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteThread(@PathVariable long user_id){
        userService.deleteUser(user_id);
    }


//    @GetMapping
//    @Secured("ROLE_ADMIN")
//    public ResponseEntity<List<User>> findUsers(@RequestParam(required = false) String username) {
//        var users = userService.searchUser(username);
//        return ResponseEntity.ok(users);
//    }
}
