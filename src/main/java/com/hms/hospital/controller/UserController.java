package com.hms.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hms.hospital.entity.Doctor;
import com.hms.hospital.entity.Patient;
import com.hms.hospital.entity.User;
import com.hms.hospital.repository.PatientRepository;
import com.hms.hospital.repository.UserRepository;
import com.hms.hospital.service.DoctorService;
import com.hms.hospital.service.PatientService;
import com.hms.hospital.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorService doctorService;

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
        if(user.getRole() == User.Role.PATIENT){
            Patient patient = new Patient();
            patient.setName(user.getUsername());
            patient.setUser(user);
            patientService.addPatient(patient);
        }
        else if(user.getRole() == User.Role.DOCTOR){
            Doctor doctor = new Doctor();
            doctor.setName(user.getUsername());
            doctor.setUser(user);
            doctorService.saveDoctor(doctor);
        }
        return "login";
    }



    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user-form";
    }

    @GetMapping("/display")
    public String displayUserList(Model model) {
        List<User> users = userService.getAllUsers(); // Get from DB or hardcoded
        model.addAttribute("users", users);
        return "user-list";
    }


    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id,Model model) {
        userService.deleteUser(id);
        List<User> users = userService.getAllUsers(); // Get from DB or hardcoded
        model.addAttribute("users", users);
        return "user-list";
    }

    
    public Patient getLoggedPatient(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return patientRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    @PostMapping("/update-password")
    public String updatePassword(
            @RequestParam Long userId,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Passwords do not match.");
            return "redirect:/admin/edit/" + userId;
        }

        userService.updatePassword(userId, newPassword);
        redirectAttributes.addFlashAttribute("success", "Password updated successfully.");
        return "redirect:/users/display";
    }
}
