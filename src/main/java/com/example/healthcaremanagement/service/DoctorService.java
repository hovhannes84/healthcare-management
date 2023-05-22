package com.example.healthcaremanagement.service;


import com.example.healthcaremanagement.entity.Doctor;
import com.example.healthcaremanagement.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DoctorService {

    List <Doctor> findDoctorsByUser(User user);
    void  addDoctor(User currentUser, MultipartFile multipartFile,Doctor doctor) throws IOException;


    void deleteById(int id);
}
