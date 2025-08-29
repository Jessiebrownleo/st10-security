package com.example.st10security.controller;

import com.example.st10security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.example.st10security.model.User;
import com.example.st10security.dto.UserRequest;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin"; // Show all users in admin.html
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("userRequest", new UserRequest());
        return "create-user"; // View name (e.g., create-user.html)
    }

    @PostMapping
    public String createUser(@ModelAttribute UserRequest request) {
        userService.save(request);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserRequest userRequest = new UserRequest();
        userRequest.setUsername(user.getUsername());
        // Don't set password for security

        model.addAttribute("user", user);
        model.addAttribute("userRequest", userRequest);
        return "edit-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute UserRequest request) {
        userService.updateUser(id, request);
        return "redirect:/users?success=updated";
    }
}
