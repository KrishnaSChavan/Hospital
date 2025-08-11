package com.hms.hospital.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import com.hms.hospital.entity.Appointment;
import com.hms.hospital.entity.Bill;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patientId")
    private Long patientId;


    @Column(name = "name")
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "contactNumber")
    private String contactNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "medicalHistory", columnDefinition = "TEXT")
    private String medicalHistory;
//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL )
//    private List<Appointment> appointments;
//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
//    private List<Bill> bills;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Bill> bills;
}