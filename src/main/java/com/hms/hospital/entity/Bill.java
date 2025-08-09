package com.hms.hospital.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;


import java.security.PublicKey;
import java.util.Date;

@Data
@Entity
@Table(name = "Bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billId")
    private Long billId;

    @ManyToOne
    @JoinColumn(name = "patientId", nullable = false)
    @JsonBackReference
    private Patient patient;

    @Column(name = "totalAmount")
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "paymentStatus")
    private PaymentStatus paymentStatus;

    @Column(name = "billDate")
    @CreationTimestamp
    private Date billDate;

    public enum  PaymentStatus{
        PAID,UNPAID
    }

}