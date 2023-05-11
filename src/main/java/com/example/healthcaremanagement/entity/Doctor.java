package com.example.healthcaremanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    private String surname;
    private String email;
    private String specialty;
    private String phone_number;
    private String profilePic;
    @ManyToOne
    private User user;



}
