package com.hms.hospital.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Thymeleaf template name, no .html
    }
}
