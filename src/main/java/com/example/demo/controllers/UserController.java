package com.example.demo.controllers;

import com.example.demo.Dtos.UserDto;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Requires roles : ADMIN")
    @GetMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String username) {
        var users = userService.getAllUsers(username);
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Requires roles : ALL")
    @PostMapping
    public ResponseEntity<User> registerUser(@Validated @RequestBody UserDto user) {
        var newUser = userService.registerUser(user);
        var uri = URI.create("/api/v1/users/" + newUser.getUser_id());
        return ResponseEntity.created(uri).body(newUser);
    }

    @Operation(summary = "Requires roles : ADMIN")
    @PutMapping("/{user_id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void User(@RequestBody User user){

    }

    @Operation(summary = "Requires roles : ADMIN")
    @DeleteMapping("/{user_id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable long user_id){
        userService.deleteUser(user_id);
    }

}
