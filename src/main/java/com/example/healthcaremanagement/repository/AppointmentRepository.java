package com.example.healthcaremanagement.repository;

import com.example.healthcaremanagement.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository <Appointment,Integer>{

    List<Appointment> findAllByUser_Id(int id);
}
