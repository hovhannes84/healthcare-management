package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.entity.Patient;
import com.example.healthcaremanagement.repository.PatientRepository;
import com.example.healthcaremanagement.security.CurrentUser;
import com.example.healthcaremanagement.service.PatientService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
public class PatientController {

    @Autowired
    private final PatientService patientService;


    @GetMapping("/patients")
    public String patientsPage(ModelMap modelMap,@AuthenticationPrincipal CurrentUser currentUser) {
        modelMap.addAttribute("patients", patientService.findPatientsByUser(currentUser.getUser()));
        return "patients";
    }

    @GetMapping("/patients/add")
    public String addPatientsPage() {
        return "addPatients";
    }

    @PostMapping("/patients/add")
    public String addPatient(@ModelAttribute Patient patient, @AuthenticationPrincipal CurrentUser currentUser) throws IOException {
        patientService.addPatient(currentUser.getUser(), patient);
        return "redirect:/patients";
    }


    @GetMapping("/patients/remove")
    public String removePatient(@RequestParam("id") int id) {
        patientService.deleteById(id);
        return "redirect:/patients";
    }

}
