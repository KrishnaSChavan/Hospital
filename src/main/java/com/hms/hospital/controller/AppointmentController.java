package com.hms.hospital.controller;


import java.util.List;

import com.hms.hospital.entity.Appointment;
import com.hms.hospital.entity.Patient;
import com.hms.hospital.repository.DoctorRepository;
import com.hms.hospital.service.AppointmentService;

import com.hms.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    PatientService patientService;

    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping("/")
    public String getAppointments(Model model){
        Patient patient = patientService.getLoggedPatient();
        List<Appointment> appointment = appointmentService.getAppointmentsByPatientId(patient.getPatientId());
        model.addAttribute("appointment",appointment);
        return "patient/appointments";
    }

    @PostMapping
    public String addAppointment(){
        return "";
    }
}
