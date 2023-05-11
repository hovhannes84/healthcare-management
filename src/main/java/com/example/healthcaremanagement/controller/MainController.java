package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.security.CurrentUser;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class MainController {

    @Value("${healthcare-management.upload.image.path}")
    private String imageUploadPath;


    @GetMapping("/")
    public String main(ModelMap modelMap,
                       @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            modelMap.addAttribute("user", currentUser.getUser());
        }
        return "index";
    }

    @GetMapping(value = "/getImage",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("profilePic") String profilePic) throws IOException {
        File file = new File(imageUploadPath + profilePic);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            return IOUtils.toByteArray(fis);
        }
        return null;
    }

}
