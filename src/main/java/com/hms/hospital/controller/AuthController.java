package com.hms.hospital.controller;


import com.hms.hospital.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {



    @GetMapping("/login")
    public String loginPage(User user) {
        System.out.println("User role: " + user.getRole());

        return "login"; // Thymeleaf template
    }
}
