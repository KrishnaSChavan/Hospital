package com.hms.hospital.service;


import com.hms.hospital.entity.Doctor;
import com.hms.hospital.entity.User;
import com.hms.hospital.repository.DoctorRepository;
import com.hms.hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    UserRepository userRepository;

    public List<Doctor> getDoctorDetails(){
        return doctorRepository.findAll();
    }

    public void saveDoctor(Doctor doctor){
        doctorRepository.save(doctor);
    }
    public void updateDoctor(Doctor doctor){
        doctorRepository.save(doctor);
    }
    public void deleteDoctor(Long id){
        doctorRepository.deleteById(id);
    }
    
    public Optional<Doctor> getDoctorById(Long id){
        return doctorRepository.findById(id);
    }
    public Doctor getLoggedDoctor(){
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println(user.getRole());
        return doctorRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Doctor not found"));

    }


}
