package com.example.assignment3.mySql.patients.service;

import com.example.assignment3.mySql.patients.mapper.PatientMapper;
import com.example.assignment3.mySql.patients.model.Patient;
import com.example.assignment3.mySql.patients.model.dto.PatientDto;
import com.example.assignment3.mySql.patients.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {


    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    private Patient findById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found: " + id));
    }

    public PatientDto create(PatientDto patient){
        return patientMapper.toDto(patientRepository.save(patientMapper.fromDto(patient)));
    }

    public PatientDto update(Long id, PatientDto patient){
        Patient actPatient = findById(id);
        actPatient.setName(patient.getName());
        actPatient.setIdentityCardNo(patient.getIdentityCardNo());
        actPatient.setPersonalNumCode(patient.getPersonalNumCode());
        actPatient.setDateOfBirth(patient.getDateOfBirth());
        actPatient.setAddress(patient.getAddress());

        return patientMapper.toDto(patientRepository.save(actPatient));
    }

    public void delete(Long id){
        patientRepository.deleteById(id);
    }

    public List<PatientDto> findAll(){
        return patientRepository.findAll().stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }

}
