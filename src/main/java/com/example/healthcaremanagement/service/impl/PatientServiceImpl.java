package com.example.healthcaremanagement.service.impl;

import com.example.healthcaremanagement.entity.Patient;
import com.example.healthcaremanagement.entity.User;
import com.example.healthcaremanagement.entity.UserType;
import com.example.healthcaremanagement.repository.PatientRepository;
import com.example.healthcaremanagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;


    @Override
    public List<Patient> findPatientsByUser(User user) {
        List<Patient> all;
        if (user.getUserType() == UserType.ADMIN) {
            all = patientRepository.findAll();
        } else {
            all = patientRepository.findAllByUser_Id(user.getId());
        }
        return all;

    }

    @Override
    public void addPatient(User currentUser, Patient patient) throws IOException {
        patient.setUser(currentUser);
        patientRepository.save(patient);
    }

    @Override
    public void deleteById(int id) {
        patientRepository.deleteById(id);

    }
}
