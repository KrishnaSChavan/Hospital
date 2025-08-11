package com.hms.hospital.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private long userId;
    @Column(name = "username",unique = true)
    private String username;

    @Column(name="password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;

    public enum Role {
        ADMIN, PATIENT, DOCTOR
    }
}
