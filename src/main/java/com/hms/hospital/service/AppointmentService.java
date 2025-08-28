package com.hms.hospital.service;


import java.util.*;
import java.util.stream.Collectors;

import com.hms.hospital.entity.Doctor;
import com.hms.hospital.entity.Patient;
import com.hms.hospital.util.TimeSlot;
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

//    public List<Appointment> getAppointmentsByPatientId(Long patientId){
//        return appointmentRepository.findByPatient_PatientId(patientId);
//    }
public List<Appointment> getAppointmentsByPatientId(Long patientId) {
    List<Appointment> appointments = appointmentRepository.findByPatient_PatientId(patientId);

    appointments.sort(Comparator
            .comparing(Appointment::getAppointmentDate).reversed()
            .thenComparing(Appointment::getTimeSlot));

    return appointments;
}
    public List<Appointment> getAppointmentsByDoctorId(Long doctorId){
        return appointmentRepository.findByDoctor_DoctorId(doctorId);
    }

    public void bookAppointment(Patient patient, Doctor doctor, Date date, String timeSlot) {
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(date);
        appointment.setTimeSlot(timeSlot);
        appointment.setStatus(Appointment.Status.CONFIRMED);
        appointmentRepository.save(appointment);
    }

    public List<String> getAvailableSlots(Doctor doctor, Date date) {
        String schedule = doctor.getAvailabilitySchedule();

        // Step 1: Generate all possible time slots from the doctor's schedule
        List<String> allPossibleSlots = TimeSlot.generateTimeSlots(schedule);

        // Step 2: Fetch all booked appointments for the doctor on the specified date
        List<Appointment> bookedAppointments = appointmentRepository.findByDoctorAndAppointmentDate(doctor, date);

        // Step 3: Extract the booked time slots
        List<String> bookedTimeSlots = bookedAppointments.stream()
                .map(Appointment::getTimeSlot) // Use a method reference for cleaner code
                .collect(Collectors.toList());

        // Step 4: Filter out the booked slots
        List<String> availableSlots = new ArrayList<>(allPossibleSlots);
        availableSlots.removeAll(bookedTimeSlots);

        return availableSlots;
    }
    public void cancelAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid appointment ID: " + id));
        appointment.setStatus(Appointment.Status.CANCELLED);
        appointmentRepository.save(appointment);
    }

}
