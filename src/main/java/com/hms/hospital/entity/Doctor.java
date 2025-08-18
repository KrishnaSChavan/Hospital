package com.hms.hospital.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.List;


@Entity
@Table(name = "Doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "name")
    private String name;

    @Column(name = "specialization")
    private String specialization;
    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "availability_schedule" , columnDefinition = "TEXT")
    private String availabilitySchedule;

//    @OneToMany(mappedBy = "doctor" , cascade=CascadeType.ALL)
//    private List<Appointment> appointments;
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Appointment> appointments;

    public Long getDoctorId() {
        return doctorId;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public String getAvailabilitySchedule() {
        return availabilitySchedule;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setAvailabilitySchedule(String availabilitySchedule) {
        this.availabilitySchedule = availabilitySchedule;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
