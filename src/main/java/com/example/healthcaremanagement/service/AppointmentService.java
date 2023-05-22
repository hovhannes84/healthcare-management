package com.example.healthcaremanagement.service;


import com.example.healthcaremanagement.entity.Appointment;
import com.example.healthcaremanagement.entity.User;

import java.io.IOException;
import java.util.List;

public interface AppointmentService {

    List <Appointment> findAppointmentsByUser(User user);
    void  addAppointment(User currentUser, Appointment appointment) throws IOException;

    void deleteById(int id);
}
