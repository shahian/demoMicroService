package com.shahian.mscustomer.controller;

import com.shahian.mscustomer.model.User;
import com.shahian.mscustomer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/v1/user")
    public ResponseEntity<User> save(@RequestBody User user) {
        User savedUser = userService.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping(value = "/v1/user")
    public ResponseEntity<User> findById(@RequestParam Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/v1/users")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PutMapping(value = "/v1/user")
    public ResponseEntity<User> update(@RequestParam Long id, @RequestBody User updatedUser) {
        User user = userService.update(id, updatedUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @DeleteMapping(value = "/v1/user")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        User user = userService.findById(id);
        if (user == null) {
            userService.delete(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}