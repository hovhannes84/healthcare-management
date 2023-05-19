package com.example.healthcaremanagement.repository;

import com.example.healthcaremanagement.entity.Patient;
import com.example.healthcaremanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Integer>{

    Optional<User> findByEmail(String email);

}
