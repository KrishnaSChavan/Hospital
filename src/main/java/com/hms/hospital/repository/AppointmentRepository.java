package com.hms.hospital.repository;

import java.util.List;

import com.hms.hospital.entity.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    List<Appointment> findByPatient_PatientId(Long patientId);
    List<Appointment> findByDoctor_DoctorId(Long doctorId);
}