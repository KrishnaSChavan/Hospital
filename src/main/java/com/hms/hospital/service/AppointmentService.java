package com.hms.hospital.service;


import java.util.*;

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

    public List<Appointment> getAppointmentsByPatientId(Long patientId){
        return appointmentRepository.findByPatient_PatientId(patientId);
    }

    public List<Appointment> getAppointmentsByDoctorId(Long doctorId){
        return appointmentRepository.findByDoctor_DoctorId(doctorId);
    }

    public List<String> getAvailableSlots(Doctor doctor, Date date) {
        List<Appointment> bookedAppointments = appointmentRepository.findByDoctorDoctorIdAndAppointmentDate(doctor.getDoctorId(), date);
        Set<String> bookedSlots = new HashSet<>();
        for (Appointment a : bookedAppointments) {
            bookedSlots.add(a.getTimeSlot());
        }

        String availability = doctor.getAvailabilitySchedule(); // e.g., "2025-08-23 : 10:30 - 18:59"
        List<String> allSlots = TimeSlot.generateTimeSlots(availability);
        List<String> availableSlots = new ArrayList<>();

        for (String slot : allSlots) {
            if (!bookedSlots.contains(slot)) {
                availableSlots.add(slot);
            }
        }

        return availableSlots;
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


}
