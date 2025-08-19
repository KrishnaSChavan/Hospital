package com.hms.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/all")
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "all/home"; // must have home.html in templates
    }
}
