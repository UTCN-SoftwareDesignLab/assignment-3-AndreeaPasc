package com.example.assignment3.mySql.patients.controller;

import com.example.assignment3.mySql.patients.model.dto.PatientDto;
import com.example.assignment3.mySql.patients.repository.PatientRepository;
import com.example.assignment3.mySql.patients.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.assignment3.UrlMapping.ENTITY;
import static com.example.assignment3.UrlMapping.PATIENTS;

@RestController
@RequestMapping(PATIENTS)
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public PatientDto create(@RequestBody PatientDto patient){
        return patientService.create(patient);
    }

    @PutMapping(ENTITY)
    public PatientDto update(@PathVariable Long id, @RequestBody PatientDto patient) {
        return patientService.update(id, patient);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id){
        patientService.delete(id);
    }
}
