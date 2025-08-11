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

import java.util.Date;

@Data
@Entity
@Table(name = "Appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointmentId") // ✅ Matches SQL column name
    private Long appointmentId;

    @ManyToOne
    @JoinColumn(name = "patientId", nullable = false) // ✅ Matches SQL column name
    @JsonBackReference
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctorId", nullable = false) // ✅ Matches SQL column name
    private Doctor doctor;

    @Column(name = "appointmentDate")
    private Date appointmentDate;

    @Column(name = "timeSlot")
    private String timeSlot;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public enum Status {
        CONFIRMED, CANCELLED
    }

}
