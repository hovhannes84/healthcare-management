package com.example.healthcaremanagement.repository;

import com.example.healthcaremanagement.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository <Doctor,Integer>{
    List<Doctor> findAllByUser_Id(int userId);


}
