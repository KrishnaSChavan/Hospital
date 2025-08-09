package com.hms.hospital.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "Doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctorId")
    private Long doctorId;

    @Column(name = "name")
    private String name;

    @Column(name = "specialization")
    private String specialization;
    @Column(name = "contactNumber")
    private String contactNumber;

    @Column(name = "availabilitySchedule" , columnDefinition = "TEXT")
    private String availabilitySchedule;

//    @OneToMany(mappedBy = "doctor" , cascade=CascadeType.ALL)
//    private List<Appointment> appointments;
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Appointment> appointments;
}
