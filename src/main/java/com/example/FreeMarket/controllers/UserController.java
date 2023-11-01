package com.example.FreeMarket.controllers;

import com.example.FreeMarket.models.User;
import com.example.FreeMarket.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public User login(Principal principal) {
        return userService.getUserByPrincipal(principal);
    }

    @GetMapping("/profile")
    public User profile(Principal principal) {
        return userService.getUserByPrincipal(principal);
    }

    @GetMapping("/registration")
    public User registration(Principal principal) {
        return userService.getUserByPrincipal(principal);
    }

    @PostMapping("/registration")
    public String createUser(@RequestBody User user) {
        if (!userService.createUser(user)) {
            return "Пользователь с email: " + user.getEmail() + " уже существует";
        }
        return "Пользователь успешно создан";
    }

    @GetMapping("/user/{userId}")
    public User userInfo(@PathVariable("userId") Long userId, Principal principal) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден");
        }
        return user;
    }
}

