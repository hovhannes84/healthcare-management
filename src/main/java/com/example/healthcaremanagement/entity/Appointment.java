package com.example.healthcaremanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date_time")
    private LocalDate date_time;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private User user;




}
