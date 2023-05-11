package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.entity.Appointment;
import com.example.healthcaremanagement.entity.Doctor;
import com.example.healthcaremanagement.entity.Patient;
import com.example.healthcaremanagement.repository.AppointmentRepository;
import com.example.healthcaremanagement.repository.DoctorRepository;
import com.example.healthcaremanagement.repository.PatientRepository;
import com.example.healthcaremanagement.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AppointmentController {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;



    @GetMapping("/appointments")
    public String appointmentsPage(ModelMap modelMap){
        List<Appointment> all = appointmentRepository.findAll();
        modelMap.addAttribute("appointments",all);
        return "appointments";
    }

    @GetMapping("/appointments/add")
    public String addAppointmentsPage(ModelMap modelMap){
        List<Patient> patients = patientRepository.findAll();
        modelMap.addAttribute("patients",patients);
        List<Doctor> doctors = doctorRepository.findAll();
        modelMap.addAttribute("doctors",doctors);
        return "addAppointments";
    }


    @PostMapping ("/appointments/add")
    public String addAppointments(@ModelAttribute Appointment appointment,@AuthenticationPrincipal CurrentUser currentUser){
        System.out.println(appointment);
        appointment.setUser(currentUser.getUser());
        appointmentRepository.save(appointment);
        return "redirect:/appointments";
    }


    @GetMapping  ("/appointments/remove")
    public String removeAppointment(@RequestParam ("id")int id){
        appointmentRepository.deleteById(id);
        return "redirect:/appointments";
    }

}
