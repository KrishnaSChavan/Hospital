package com.hms.hospital.controller;

import com.hms.hospital.entity.Patient;
import com.hms.hospital.service.PatientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.hms.hospital.service.UserService;


//@Tag(name = "Patient", description = "Patient management APIs")
//@RestController
//@RequestMapping("/patient")
//public class PatientController {
//    @Autowired
//    private PatientService patientService;
//
//
//
//    @Operation(summary = "Add a new patient")
//    @PostMapping("/add")
//    public ResponseEntity<String> addPatient(@RequestBody Patient patient) {
//        patientService.addPatient(patient);
//        return ResponseEntity.ok("Patient added successfully");
//    }
//
//}


@Tag(name = "Patient", description = "Patient management APIs")
@Controller
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/")
    public String patientList(){
        return "doctor-list";
    }

}

