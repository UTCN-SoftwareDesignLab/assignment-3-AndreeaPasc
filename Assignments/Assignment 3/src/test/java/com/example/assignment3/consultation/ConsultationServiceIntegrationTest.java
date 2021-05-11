package com.example.assignment3.consultation;

import com.example.assignment3.TestCreationFactory;
import com.example.assignment3.mySql.consultations.model.Consultation;
import com.example.assignment3.mySql.consultations.model.dto.ConsultationDto;
import com.example.assignment3.mySql.consultations.repository.ConsultationRepository;
import com.example.assignment3.mySql.consultations.service.ConsultationService;
import com.example.assignment3.mySql.patients.model.Patient;
import com.example.assignment3.mySql.patients.model.dto.PatientDto;
import com.example.assignment3.mySql.patients.repository.PatientRepository;
import com.example.assignment3.mySql.patients.service.PatientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public class ConsultationServiceIntegrationTest {

    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private ConsultationRepository consultationRepository;

    @BeforeEach
    void setUp() {
        consultationRepository.deleteAll();
    }

    @Test
    void findAll() {
        List<Consultation> consultations = TestCreationFactory.listOf(Patient.class);
        consultationRepository.saveAll(consultations);

        List<ConsultationDto> all = consultationService.findAll();

        Assertions.assertEquals(consultations.size(), all.size());
    }

    @Test
    void create(){
        Consultation consultation = Consultation.builder()
                .id(5L)
                .doctorName("Doctor1")
                .startHour(1)
                .endHour(2)
                .build();
        consultationRepository.save(consultation);
        ConsultationDto consultationDto = ConsultationDto.builder()
                .id(5L)
                .doctorName("Doctor1")
                .startHour(1)
                .endHour(2)
                .build();

        Assertions.assertNotNull(consultationService.create(consultationDto));
    }

    @Test
    void delete(){
        ConsultationDto consultationDto = ConsultationDto.builder()
                .id(5L)
                .doctorName("Doctor1")
                .startHour(1)
                .endHour(2)
                .build();
        consultationDto = consultationService.create(consultationDto);
        consultationService.delete(consultationDto.getId());
    }

    @Test
    void edit(){
        ConsultationDto consultationDto = ConsultationDto.builder()
                .id(5L)
                .doctorName("Doctor1")
                .startHour(1)
                .endHour(2)
                .build();
        consultationDto = consultationService.create(consultationDto);
        Assertions.assertEquals(consultationDto.getId(), consultationService.update(consultationDto.getId(), consultationDto).getId());
    }


}
