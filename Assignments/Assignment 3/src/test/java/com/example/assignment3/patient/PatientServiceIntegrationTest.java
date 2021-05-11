package com.example.assignment3.patient;

import com.example.assignment3.TestCreationFactory;
import com.example.assignment3.mySql.patients.model.Patient;
import com.example.assignment3.mySql.patients.model.dto.PatientDto;
import com.example.assignment3.mySql.patients.repository.PatientRepository;
import com.example.assignment3.mySql.patients.service.PatientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;

public class PatientServiceIntegrationTest {
    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @BeforeEach
    void setUp() {
        patientRepository.deleteAll();
    }

    @Test
    void findAll() {
        List<Patient> books = TestCreationFactory.listOf(Patient.class);
        patientRepository.saveAll(books);

        List<PatientDto> all = patientService.findAll();

        Assertions.assertEquals(books.size(), all.size());
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

        patientRepository.save(patient);
        PatientDto patientDto = PatientDto.builder()
                .id(5L)
                .name("Patient1")
                .address("Address")
                .dateOfBirth(LocalDate.now())
                .identityCardNo(1234L)
                .personalNumCode(5678L)
                .build();

        Assertions.assertNotNull(patientService.create(patientDto));
    }

    @Test
    void delete(){
        PatientDto patientDto = PatientDto.builder()
                .id(5L)
                .name("Patient1")
                .address("Address1")
                .dateOfBirth(LocalDate.now())
                .identityCardNo(1234L)
                .personalNumCode(5678L)
                .build();
        patientDto = patientService.create(patientDto);
        patientService.delete(patientDto.getId());
    }

    @Test
    void edit(){
        PatientDto patientDto = PatientDto.builder()
                .id(5L)
                .name("Patient1")
                .address("Address1")
                .dateOfBirth(LocalDate.now())
                .identityCardNo(1234L)
                .personalNumCode(5678L)
                .build();
        patientDto = patientService.create(patientDto);
        Assertions.assertEquals(patientDto.getId(), patientService.update(patientDto.getId(), patientDto).getId());
    }

}
