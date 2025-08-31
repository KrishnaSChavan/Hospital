package com.hms.hospital.controller;

import com.hms.hospital.entity.User;
import com.hms.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping
    public String adminDashboard() {
        return "admin-dashboard";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "change-Password-form";
    }

    @GetMapping("/display")
    public String displayUserList(Model model) {
        List<User> users = userService.getAllUsers(); // Get from DB or hardcoded 
        model.addAttribute("users", users);
        return "change-Password-form";
    }
}
