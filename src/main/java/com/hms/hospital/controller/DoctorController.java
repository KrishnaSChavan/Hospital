package com.hms.hospital.controller;


import com.hms.hospital.entity.Doctor;
import com.hms.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @GetMapping("/")
    public String getDoctorList(){
        return "doctor-list";
    }

    @GetMapping("/new")
    public String doctorRegisterForm(Model model){
        model.addAttribute("doctor",new Doctor());
        return "doctor/form";
    }
    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute("doctor") Doctor doctor) {
        doctorService.saveDoctor(doctor);
        return "redirect:/doctor/home";
    }

}
