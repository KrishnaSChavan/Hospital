package com.hms.hospital.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.hms.hospital.entity.Appointment;
import com.hms.hospital.entity.Doctor;
import com.hms.hospital.entity.Patient;
import com.hms.hospital.repository.DoctorRepository;
import com.hms.hospital.service.AppointmentService;

import com.hms.hospital.service.DoctorService;
import com.hms.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    PatientService patientService;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    DoctorService doctorService;

    @GetMapping("/")
    public String getAppointments(Model model){
        Patient patient = patientService.getLoggedPatient();
        List<Appointment> appointment = appointmentService.getAppointmentsByPatientId(patient.getPatientId());
        model.addAttribute("appointment",appointment);
        return "patient/appointments";
    }

    @GetMapping("/book")
    public String showAllDoctors(Model model) {
        List<Doctor> doctors = doctorService.getDoctorDetails();
        model.addAttribute("doctors", doctors);
        return "patient/select-doctor"; // A new view to list doctors
    }


@GetMapping("/book/{doctorId}")
public String showAvailableSlots(@PathVariable Long doctorId, Model model) {
    Doctor doctor = doctorService.getDoctorById(doctorId)
            .orElseThrow(() -> new RuntimeException("Doctor not found"));

    Patient patient = patientService.getLoggedPatient();
    Date date = new Date();


    List<Appointment> existingAppointments = appointmentService.getAppointmentsByPatientId(patient.getPatientId());
    boolean hasConfirmed = existingAppointments.stream()
            .anyMatch(appt -> appt.getDoctor().getDoctorId().equals(doctorId)
                    && appt.getAppointmentDate().equals(date)
                    && appt.getStatus() == Appointment.Status.CONFIRMED);

    if (hasConfirmed) {
        model.addAttribute("doctor", doctor);
        model.addAttribute("date", new SimpleDateFormat("yyyy-MM-dd").format(date));
        model.addAttribute("alreadyBooked", true);
        return "patient/book-appointment";
    }

    List<String> availableSlots = appointmentService.getAvailableSlots(doctor, date);
    model.addAttribute("doctor", doctor);
    model.addAttribute("availableSlots", availableSlots);
    model.addAttribute("date", new SimpleDateFormat("yyyy-MM-dd").format(date));
    return "patient/book-appointment";
}


    @PostMapping("/confirm")
    public String confirmAppointment(@RequestParam Long doctorId,
                                     @RequestParam String timeSlot,
                                     @RequestParam String dateStr) throws Exception {
        Doctor doctor = doctorService.getDoctorById(doctorId).orElseThrow(() -> new RuntimeException("Doctor not found"));
        Patient patient = patientService.getLoggedPatient();
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);

        appointmentService.bookAppointment(patient, doctor, date, timeSlot);
        return "redirect:/patient/";
    }


    @DeleteMapping("/{id}")
    public String removeAppointment(@PathVariable Long id, Model model){
        appointmentService.deleteAppointment(id);
        Patient patient = patientService.getLoggedPatient();
        List<Appointment> appointment = appointmentService.getAppointmentsByPatientId(patient.getPatientId());
        model.addAttribute("appointment",appointment);
        return "patient/appointments";
    }
    @PostMapping("/delete/{id}")
    public String removeAppointment(@PathVariable Long id){
        appointmentService.deleteAppointment(id);
        return "redirect:/patient/";
    }
    @PostMapping("/cancel/{id}")
    public String cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
        return "redirect:/patient/";
    }

    @GetMapping("/patientList")
    public String getPatientList(Model model){
        Doctor doctor = doctorService.getLoggedDoctor();
        List<Appointment> appointment = appointmentService.getAppointmentsByDoctorId(doctor.getDoctorId());
        model.addAttribute("appointment",appointment);
        model.addAttribute("doctor",doctor);
        return "doctor/doctor-dashboard";

    }

}
