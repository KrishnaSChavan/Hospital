package com.hms.hospital.repository;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.hospital.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    // Additional query methods can be defined here if needed
    Patient findByName(String name);
}
