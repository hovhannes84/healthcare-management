package com.example.healthcaremanagement.service.impl;

import com.example.healthcaremanagement.entity.Appointment;
import com.example.healthcaremanagement.entity.User;
import com.example.healthcaremanagement.entity.UserType;
import com.example.healthcaremanagement.repository.AppointmentRepository;
import com.example.healthcaremanagement.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> findAppointmentsByUser(User user) {
        List<Appointment> all;
        if (user.getUserType() == UserType.ADMIN) {
            all = appointmentRepository.findAll();
        } else {
            all = appointmentRepository.findAllByUser_Id(user.getId());
        }
        return all;
    }
    @Override
    public void addAppointment(User currentUser, Appointment appointment) throws IOException {
        appointment.setUser(currentUser);
        appointmentRepository.save(appointment);

    }

    @Override
    public void deleteById(int id) {
        appointmentRepository.deleteById(id);

    }
}
