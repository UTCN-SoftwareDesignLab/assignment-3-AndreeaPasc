package com.example.assignment3.mySql.consultations.controller;

import com.example.assignment3.mySql.consultations.model.dto.ConsultationDto;
import com.example.assignment3.mySql.consultations.service.ConsultationService;
import com.example.assignment3.mySql.patients.model.dto.PatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.assignment3.UrlMapping.*;
import static com.example.assignment3.UrlMapping.ENTITY;

@RestController
@RequestMapping(CONSULTATIONS)
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;

    @PostMapping
    public ConsultationDto create(@RequestBody ConsultationDto consultation){
        return consultationService.create(consultation);
    }

    @PutMapping(ENTITY)
    public ConsultationDto update(@PathVariable Long id, @RequestBody ConsultationDto consultation) {
        return consultationService.update(id, consultation);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id){
        consultationService.delete(id);
    }

}
