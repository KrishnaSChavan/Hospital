package com.hms.hospital.repository;

//import java.util.List;

import com.hms.hospital.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.hospital.entity.Patient;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    // Additional query methods can be defined here if needed
    @Autowired
    Patient findByName(String name);
    Optional<Patient> findByUser(User user);

}
