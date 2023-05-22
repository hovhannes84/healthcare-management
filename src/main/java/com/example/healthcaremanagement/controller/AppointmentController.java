package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.entity.Appointment;
import com.example.healthcaremanagement.entity.Doctor;
import com.example.healthcaremanagement.entity.Patient;
import com.example.healthcaremanagement.repository.AppointmentRepository;
import com.example.healthcaremanagement.repository.DoctorRepository;
import com.example.healthcaremanagement.repository.PatientRepository;
import com.example.healthcaremanagement.security.CurrentUser;
import com.example.healthcaremanagement.service.AppointmentService;
import com.example.healthcaremanagement.service.DoctorService;
import com.example.healthcaremanagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AppointmentController {


    private final PatientService patientService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;



    @GetMapping("/appointments")
    public String appointmentsPage(ModelMap modelMap,@AuthenticationPrincipal CurrentUser currentUser){
        modelMap.addAttribute("appointments",appointmentService.findAppointmentsByUser(currentUser.getUser()));
        return "appointments";
    }

    @GetMapping("/appointments/add")
    public String addAppointmentsPage(ModelMap modelMap,@AuthenticationPrincipal CurrentUser currentUser){
        modelMap.addAttribute("patients",patientService.findPatientsByUser(currentUser.getUser()));
        modelMap.addAttribute("doctors",doctorService.findDoctorsByUser(currentUser.getUser()));
        return "addAppointments";
    }


    @PostMapping ("/appointments/add")
    public String addAppointments(@ModelAttribute Appointment appointment,@AuthenticationPrincipal CurrentUser currentUser) throws IOException {
        appointmentService.addAppointment(currentUser.getUser(),appointment);
        return "redirect:/appointments";
    }


    @GetMapping  ("/appointments/remove")
    public String removeAppointment(@RequestParam ("id")int id){
        appointmentService.deleteById(id);
        return "redirect:/appointments";
    }

}
