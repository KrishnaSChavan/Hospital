package com.hms.hospital.service;


import com.hms.hospital.entity.Doctor;
import com.hms.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    public List<Doctor> getDoctorDetails(){
        return doctorRepository.findAll();
    }

    public void saveDoctor(Doctor doctor){
        doctorRepository.save(doctor);
    }
    
    public Optional<Doctor> getDoctorById(Long id){
        return doctorRepository.findById(id);
    }


}
