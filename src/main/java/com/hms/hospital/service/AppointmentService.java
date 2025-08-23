package com.hms.hospital.service;


import com.hms.hospital.entity.Appointment;
import com.hms.hospital.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointment(){
        return appointmentRepository.findAll();
    }
    public void addAppointment(){

    }
}
