package com.example.assignment3.patient;

import com.example.assignment3.TestCreationFactory;
import com.example.assignment3.mySql.patients.mapper.PatientMapper;
import com.example.assignment3.mySql.patients.model.Patient;
import com.example.assignment3.mySql.patients.model.dto.PatientDto;
import com.example.assignment3.mySql.patients.repository.PatientRepository;
import com.example.assignment3.mySql.patients.service.PatientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        patientService = new PatientService(patientRepository, patientMapper);
    }

    @Test
    void findAll() {
        List<Patient> patients = TestCreationFactory.listOf(Patient.class);
        when(patientRepository.findAll()).thenReturn(patients);

        List<PatientDto> all = patientService.findAll();

        Assertions.assertEquals(patients.size(), all.size());
    }

    @Test
    void create(){
        Patient patient = Patient.builder()
                .id(5L)
                .name("Patient1")
                .address("Address1")
                .dateOfBirth(LocalDate.now())
                .identityCardNo(1234L)
                .personalNumCode(5678L)
                .build();

        PatientDto patientDto = PatientDto.builder()
                .id(5L)
                .name("Patient1")
                .address("Address1")
                .dateOfBirth(LocalDate.now())
                .identityCardNo(1234L)
                .personalNumCode(5678L)
                .build();

        when(patientMapper.toDto(patient)).thenReturn(patientDto);
        when(patientMapper.fromDto(patientDto)).thenReturn(patient);
        when(patientRepository.save(patient)).thenReturn(patient);
        PatientDto newPatient = patientService.create(patientDto);
        Assertions.assertNotNull(newPatient);
    }

    @Test
    void update(){
        Patient patient = Patient.builder()
                .id(5L)
                .name("Patient1")
                .address("Address1")
                .dateOfBirth(LocalDate.now())
                .identityCardNo(1234L)
                .personalNumCode(5678L)
                .build();

        PatientDto patientDto = PatientDto.builder()
                .id(5L)
                .name("Patient1")
                .address("Address1")
                .dateOfBirth(LocalDate.now())
                .identityCardNo(1234L)
                .personalNumCode(5678L)
                .build();

        when(patientMapper.fromDto(patientDto)).thenReturn(patient);
        when(patientMapper.toDto(patient)).thenReturn(patientDto);
        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));
        when(patientRepository.save(patient)).thenReturn(patient);
        Assertions.assertEquals(patientDto.getId(), patientService.update(patientDto.getId(), patientDto).getId());
    }

    @Test
    void delete(){
        Patient patient = Patient.builder()
                .id(5L)
                .name("Patient1")
                .address("Address1")
                .dateOfBirth(LocalDate.now())
                .identityCardNo(1234L)
                .personalNumCode(5678L)
                .build();
        patientService.delete(patient.getId());
    }
}
