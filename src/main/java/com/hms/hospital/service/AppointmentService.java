package com.hms.hospital.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.hospital.entity.Appointment;
import com.hms.hospital.repository.AppointmentRepository;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointment(){
        return appointmentRepository.findAll();
    }
    public void addAppointment(Appointment appointment){
        appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id){
        appointmentRepository.deleteById(id);
    }

    public Appointment getAppointmentById(Long id){
        return appointmentRepository.findById(id).orElse(null);
    }

    public void updateAppointment(Appointment appointment){
        appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByPatientId(Long patientId){
        return appointmentRepository.findByPatient_PatientId(patientId);
    }

    public List<Appointment> getAppointmentsByDoctorId(Long doctorId){
        return appointmentRepository.findByDoctor_DoctorId(doctorId);
    }

}
