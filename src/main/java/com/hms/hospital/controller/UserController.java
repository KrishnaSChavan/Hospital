package com.hms.hospital.controller;

import com.hms.hospital.entity.User;
import com.hms.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user,Model model) {
        userService.saveUser(user);
        List<User> users = userService.getAllUsers(); // Get from DB or hardcoded
        model.addAttribute("users", users);
        return "user-list";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user-form";
    }
//    @GetMapping("/display")
//    public String displayUser(@ModelAttribute("user") User user, Model model) {
//        model.addAttribute("user", user);
//        return "user-list";
//    }
@GetMapping("/display")
public String displayUserList(Model model) {
    List<User> users = userService.getAllUsers(); // Get from DB or hardcoded
    model.addAttribute("users", users);
    return "user-list";
}


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id,Model model) {
        userService.deleteUser(id);
        List<User> users = userService.getAllUsers(); // Get from DB or hardcoded
        model.addAttribute("users", users);
        return "user-list";
    }
}
