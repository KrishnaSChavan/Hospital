package com.hms.hospital.controller;


import com.hms.hospital.entity.Appointment;
import com.hms.hospital.entity.Doctor;
import com.hms.hospital.service.AppointmentService;
import com.hms.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    DoctorService doctorService;
    @Autowired
    AppointmentService appointmentService;

    @GetMapping("/")
    public String getDoctorList(Model model){
        Doctor doctor = doctorService.getLoggedDoctor();
        List<Appointment> appointment = appointmentService.getAppointmentsByDoctorId(doctor.getDoctorId());
        model.addAttribute("appointment",appointment);
        model.addAttribute("doctor",doctor);
        return "doctor/doctor-dashboard";
    }

    @GetMapping("/new")
    public String doctorRegisterForm(Model model){
        Doctor doctor = doctorService.getLoggedDoctor();
        model.addAttribute("doctor",doctor);
        return "doctor/form";
    }
    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute("doctor") Doctor doctor) {
        doctorService.saveDoctor(doctor);
        return "redirect:/doctor/home";
    }
    @GetMapping("/edit-profile")
    public String editDoctorProfile(Model model){
        Doctor doctor = doctorService.getLoggedDoctor();
        model.addAttribute("doctor",doctor);
        return "doctor/edit-doctor-form";
    }
    @PostMapping("/profile-update")
    public String updateDoctorProfile(@ModelAttribute Doctor doctor, Model model){
        Doctor existingDoctor = doctorService.getLoggedDoctor();

        existingDoctor.setName(doctor.getName());
        existingDoctor.setSpecialization(doctor.getSpecialization());
        existingDoctor.setContactNumber(doctor.getContactNumber());
        existingDoctor.setAvailabilitySchedule(doctor.getAvailabilitySchedule());

        doctorService.updateDoctor(existingDoctor);
        model.addAttribute("message", "Profile updated successfully");

        return "doctor/doctor-dashboard";
    }





}
