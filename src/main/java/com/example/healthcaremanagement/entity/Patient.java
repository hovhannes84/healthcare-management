package com.example.healthcaremanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    private String surname;
    private LocalDate dateOfBirthday;
    @ManyToOne
    private User user;

}
