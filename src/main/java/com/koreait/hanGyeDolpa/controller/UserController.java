package com.koreait.hanGyeDolpa.controller;

import com.koreait.hanGyeDolpa.entity.User;
import com.koreait.hanGyeDolpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestParam String username, @RequestParam String password) {
        return userService.createUser(username, password);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
}
