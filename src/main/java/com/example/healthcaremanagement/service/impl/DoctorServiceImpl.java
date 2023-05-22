package com.example.healthcaremanagement.service.impl;

import com.example.healthcaremanagement.entity.Doctor;
import com.example.healthcaremanagement.entity.User;
import com.example.healthcaremanagement.entity.UserType;
import com.example.healthcaremanagement.repository.DoctorRepository;
import com.example.healthcaremanagement.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    @Value("${healthcare-management.upload.image.path}")
    private String imageUploadPath;


    @Override
    public List<Doctor> findDoctorsByUser(User user) {
        List<Doctor> all;
        if (user.getUserType() == UserType.ADMIN){
            all = doctorRepository.findAll();
        }else {
            all = doctorRepository.findAllByUser_Id(user.getId());
        }
        return all;
    }

    @Override
    public void addDoctor(User currentUser, MultipartFile multipartFile, Doctor doctor) throws IOException {
        if (multipartFile != null && !multipartFile.isEmpty()){
            String fileName = System.nanoTime() + "_" + multipartFile.getOriginalFilename();
            File file = new File(imageUploadPath + fileName);
            multipartFile.transferTo(file);
            doctor.setProfilePic(fileName);
        }
        doctor.setUser(currentUser);
        doctorRepository.save(doctor);
    }

    @Override
    public void deleteById(int id) {
        doctorRepository.deleteById(id);

    }
}
