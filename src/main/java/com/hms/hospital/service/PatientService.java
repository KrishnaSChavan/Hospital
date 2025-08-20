package com.hms.hospital.service;

import java.util.List;

import com.hms.hospital.entity.User;
import com.hms.hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hms.hospital.entity.Patient;
import com.hms.hospital.repository.PatientRepository;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserService userService;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public void addPatient(Patient patient) {
        patientRepository.save(patient);
    }

    public void removePatient(Patient patient) {
        patientRepository.delete(patient);
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public void updatePatient(Patient patient) {
        patientRepository.save(patient);
    }

    public Patient getLoggedPatient(){
//        String username = userService.getLoggedUser();
//        return patientRepository.findByName(username);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println(user.getRole());
        return patientRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Patient not found"));

    }



}
