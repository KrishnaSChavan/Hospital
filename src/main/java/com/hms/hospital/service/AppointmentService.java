package com.hms.hospital.service;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.hospital.entity.Appointment;
import com.hms.hospital.entity.Doctor;
import com.hms.hospital.entity.Patient;
import com.hms.hospital.repository.AppointmentRepository;
import com.hms.hospital.util.TimeSlot;

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
    public List<Appointment> getAppointmentsByPatientId(Long patientId) {
        List<Appointment> appointments = appointmentRepository.findByPatient_PatientId(patientId);

        appointments.sort(Comparator
            .comparing(Appointment::getAppointmentDate).reversed()
            .thenComparing(Appointment::getTimeSlot));

        return appointments;
    }
    public List<Appointment> getAppointmentsByDoctorId(Long doctorId){
        List<Appointment> appointments = appointmentRepository.findByDoctor_DoctorId(doctorId);
        appointments.sort(Comparator
                .comparing(Appointment::getAppointmentDate).reversed()
                .thenComparing(Appointment::getTimeSlot));
        return appointments;
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
        List<String> allPossibleSlots = TimeSlot.generateTimeSlots(schedule);
        List<Appointment> bookedAppointments = appointmentRepository.findByDoctorAndAppointmentDate(doctor, date);
        List<String> bookedTimeSlots = bookedAppointments.stream()
                .map(Appointment::getTimeSlot) 
                .collect(Collectors.toList());
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
