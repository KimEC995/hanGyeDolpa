package com.koreait.hanGyeDolpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.hanGyeDolpa.entity.User_Table;
import com.koreait.hanGyeDolpa.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
    private UserService userService;

    @PostMapping
    public User_Table createUser(@RequestParam String username, @RequestParam String password) {
        return userService.createUser(username, password);
    }

    @GetMapping("/{username}")
    public User_Table getUser(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

}
