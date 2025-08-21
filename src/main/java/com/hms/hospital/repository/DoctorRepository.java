package com.hms.hospital.repository;

import com.hms.hospital.entity.Doctor;
import com.hms.hospital.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    @Autowired
    Doctor findByName(String name);
    Optional<Doctor> findByUser(User user);
}