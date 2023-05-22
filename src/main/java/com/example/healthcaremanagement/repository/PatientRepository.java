package com.example.healthcaremanagement.repository;

import com.example.healthcaremanagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository <Patient,Integer>{

    List<Patient> findAllByUser_Id(int id);

}
