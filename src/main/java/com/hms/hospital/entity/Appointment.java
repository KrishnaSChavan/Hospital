//package com.hms.hospital.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//import lombok.Data;
//import java.util.Date;
//
//@Data
//@Entity
//@Table(name = "Appointment")
//public class Appointment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "appointmentId")
//    private Long appointmentId;
//
//    @ManyToOne
//    @JoinColumn(name = "patientId", nullable = false)
//    private Patient patient;
//
//
//    @ManyToOne
//    @JoinColumn(name = "doctorId",nullable = false)
//    @JsonBackReference
//    private Doctor doctor;
//
//    @Column(name = "appointmentDate")
//    private Date appointmentDate;
//
//    @Column(name = "timeSlot")
//    private String timeSlot;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "status")
//    private Status status;
//
//    public enum Status{
//        CONFIRMED, CANCELLED
//    }
//}


package com.hms.hospital.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
@Entity
@Table(name = "Appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long appointmentId;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonBackReference
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(name = "appointment_date")
    private Date appointmentDate;

    @Column(name = "time_slot")
    private String timeSlot;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public enum Status {
        CONFIRMED, CANCELLED
    }

}
