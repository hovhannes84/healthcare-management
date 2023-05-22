package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.entity.Doctor;
import com.example.healthcaremanagement.security.CurrentUser;
import com.example.healthcaremanagement.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;


    @GetMapping("/doctors")
    public String doctorsPage(ModelMap modelMap,@AuthenticationPrincipal CurrentUser currentUser){
        modelMap.addAttribute("doctors",doctorService.findDoctorsByUser(currentUser.getUser()));
        return "doctors";
    }

    @GetMapping("/doctors/add")
    public String addDoctorPage(){
        return "addDoctor";
    }

    @PostMapping ("/doctors/add")
    public String addDoctor(@ModelAttribute Doctor doctor, @RequestParam ("image")MultipartFile multipartFile, @AuthenticationPrincipal CurrentUser currentUser) throws IOException {
doctorService.addDoctor(currentUser.getUser(),multipartFile,doctor);
        return "redirect:/doctors";
    }

    @GetMapping  ("/doctors/remove")
    public String removeDoctor(@RequestParam ("id")int id){
        doctorService.deleteById(id);
        return "redirect:/doctors";
    }

}
