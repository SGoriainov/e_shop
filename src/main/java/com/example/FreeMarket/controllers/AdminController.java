package com.example.FreeMarket.controllers;


import com.example.FreeMarket.models.User;
import com.example.FreeMarket.models.enams.Role;
import com.example.FreeMarket.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;

    @GetMapping("/admin")
    public Map<String, Object> admin(Principal principal) {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("users", userService.list());
        responseData.put("user", userService.getUserByPrincipal(principal));
        return responseData;
    }

    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.banUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/user/edit/{userId}")
    public Map<String, Object> userEdit(@PathVariable("userId") Long userId, Principal principal) {
        User user = userService.getUserById(userId);
        User currentUser = userService.getUserByPrincipal(principal);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден");
        }

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("user", user);
        responseData.put("currentUser", currentUser);
        responseData.put("roles", Role.values());
        return responseData;
    }

    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam("userId") Long userId, @RequestParam Map<String, String> form) {
        User user = userService.getUserById(userId);
        userService.changeUserRoles(user, form);
        return "redirect:/admin";
    }
}
