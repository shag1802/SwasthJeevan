package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entity.Doctor;
import com.example.backend.entity.User;
import com.example.backend.service.DoctorService;
import com.example.backend.service.UserService;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    @Autowired
    private UserService userService;

    @Autowired 
    private DoctorService doctorService;

    @GetMapping("/profile")
    public ResponseEntity<Doctor> findUserByJwtToken(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Doctor doctor = doctorService.getDoctorbyUsername(user.getUsername());
        return (new ResponseEntity<>(doctor, HttpStatus.OK));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Doctor>> getAllDoctors(@RequestHeader("Authorization") String jwt) throws Exception {
        List<Doctor> doctorsList = doctorService.getAllDoctors();
        return(new ResponseEntity<>(doctorsList, HttpStatus.OK));
    }
}
