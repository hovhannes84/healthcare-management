package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.entity.Patient;
import com.example.healthcaremanagement.repository.PatientRepository;
import com.example.healthcaremanagement.security.CurrentUser;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;


    @GetMapping("/patients")
    public String patientsPage(ModelMap modelMap) {
        List<Patient> all = patientRepository.findAll();
        modelMap.addAttribute("patients", all);
        return "patients";
    }

    @GetMapping("/patients/add")
    public String addPatientsPage() {
        return "addPatients";
    }

    @PostMapping("/patients/add")
    public String addPatient(@ModelAttribute Patient patient, @AuthenticationPrincipal CurrentUser currentUser) {
        patient.setUser(currentUser.getUser());
        patientRepository.save(patient);
        return "redirect:/patients";
    }

//    @PostMapping ("/patients/add")
//    public String addPatient(@RequestParam ("name") String name,@RequestParam("surname") String surname,@RequestParam("dade") Date date) {
//        Patient patient = new Patient();
//        patient.setName(name);
//        patient.setSurname(surname);
//        patient.setDate(date);
//        patientRepository.save(patient);
//        return "redirect:/patients";
//    }

    @GetMapping("/patients/remove")
    public String removePatient(@RequestParam("id") int id) {
        patientRepository.deleteById(id);
        return "redirect:/patients";
    }

}
