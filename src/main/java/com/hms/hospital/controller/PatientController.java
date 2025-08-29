package com.hms.hospital.controller;

import com.hms.hospital.entity.Appointment;
import com.hms.hospital.entity.Bill;
import com.hms.hospital.entity.Doctor;
import com.hms.hospital.entity.Patient;
import com.hms.hospital.service.AppointmentService;
import com.hms.hospital.service.BillService;
import com.hms.hospital.service.DoctorService;
import com.hms.hospital.service.PatientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Tag(name = "Patient", description = "Patient management APIs")
@Controller
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    BillService billService;

    @GetMapping("/")
    public String patientList(Model model) {
        Patient patient = patientService.getLoggedPatient();
        model.addAttribute("patient", patient);
        List<Doctor> doctors = doctorService.getDoctorDetails();
        model.addAttribute("doctors",doctors);
        List<Appointment> appointment = appointmentService.getAppointmentsByPatientId(patient.getPatientId());
        model.addAttribute("appointment",appointment);
        List<Bill> bills = billService.getBillsByPatientId(patient.getPatientId());
        model.addAttribute("bills", bills);
        return "patient/home";
    }


    @GetMapping("/edit-profile")
    public String editProfileForm(Model model){
        Patient patient = patientService.getLoggedPatient();
        model.addAttribute("patient",patient);
        return "patient/edit-patient-form";
    }

    @PostMapping("/profile-update")
    public String updatePatientProfile(@ModelAttribute Patient patient, Model model){
        Patient patient1 = patientService.getLoggedPatient();

        patient1.setDateOfBirth(patient.getDateOfBirth());
        patient1.setAddress(patient.getAddress());
        patient1.setGender(patient.getGender());
        patient1.setContactNumber(patient.getContactNumber());


        patientService.updatePatient(patient1);
        model.addAttribute("message","Profile updated successfully");

        return "patient/patient-dashboard";
    }


}

