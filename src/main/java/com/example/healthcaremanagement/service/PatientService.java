package com.example.healthcaremanagement.service;


import com.example.healthcaremanagement.entity.Patient;
import com.example.healthcaremanagement.entity.User;

import java.io.IOException;
import java.util.List;

public interface PatientService {

    List <Patient> findPatientsByUser(User user);
    void  addPatient(User currentUser, Patient patient) throws IOException;


    void deleteById(int id);
}
