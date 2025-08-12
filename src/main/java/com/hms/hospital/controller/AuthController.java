package com.hms.hospital.controller;

import com.hms.hospital.DTO.LoginRequestDTO;
import com.hms.hospital.DTO.LoginResponseDTO;
import com.hms.hospital.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequestDTO loginRequestDTO, Model model) {
        try {
            LoginResponseDTO response = authService.login(loginRequestDTO);
            model.addAttribute("token", response.getToken());
            model.addAttribute("role", response.getRole());
            return "Home";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "Login";
        }
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginRequestDTO", new LoginRequestDTO());
        return "Login";
    }


}
