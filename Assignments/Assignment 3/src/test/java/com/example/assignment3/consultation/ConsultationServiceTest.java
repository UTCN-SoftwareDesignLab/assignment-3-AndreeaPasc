package com.example.assignment3.consultation;

import com.example.assignment3.TestCreationFactory;
import com.example.assignment3.mySql.consultations.mapper.ConsultationMapper;
import com.example.assignment3.mySql.consultations.model.Consultation;
import com.example.assignment3.mySql.consultations.model.dto.ConsultationDto;
import com.example.assignment3.mySql.consultations.repository.ConsultationRepository;
import com.example.assignment3.mySql.consultations.service.ConsultationService;
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

public class ConsultationServiceTest {


    @InjectMocks
    private ConsultationService consultationService;

    @Mock
    private ConsultationRepository consultationRepository;

    @Mock
    private ConsultationMapper consultationMapper;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        consultationService = new ConsultationService(consultationRepository, consultationMapper);
    }

    @Test
    void findAll() {
        List<Consultation> consultations = TestCreationFactory.listOf(Patient.class);
        when(consultationRepository.findAll()).thenReturn(consultations);

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

        ConsultationDto consultationDto = ConsultationDto.builder()
                .id(5L)
                .doctorName("Doctor1")
                .startHour(1)
                .endHour(2)
                .build();


        when(consultationMapper.toDto(consultation)).thenReturn(consultationDto);
        when(consultationMapper.fromDto(consultationDto)).thenReturn(consultation);
        when(consultationRepository.save(consultation)).thenReturn(consultation);
        ConsultationDto newConsultation = consultationService.create(consultationDto);
        Assertions.assertNotNull(newConsultation);
    }

    @Test
    void update(){
        Consultation consultation = Consultation.builder()
                .id(5L)
                .doctorName("Doctor1")
                .startHour(1)
                .endHour(2)
                .build();

        ConsultationDto consultationDto = ConsultationDto.builder()
                .id(5L)
                .doctorName("Doctor2")
                .startHour(1)
                .endHour(2)
                .build();

        when(consultationMapper.fromDto(consultationDto)).thenReturn(consultation);
        when(consultationMapper.toDto(consultation)).thenReturn(consultationDto);
        when(consultationRepository.findById(consultation.getId())).thenReturn(Optional.of(consultation));
        when(consultationRepository.save(consultation)).thenReturn(consultation);
        Assertions.assertEquals(consultationDto.getId(), consultationService.update(consultationDto.getId(), consultationDto).getId());
    }

    @Test
    void delete(){
        Consultation consultation = Consultation.builder()
                .id(5L)
                .doctorName("Doctor1")
                .startHour(1)
                .endHour(2)
                .build();

        consultationService.delete(consultation.getId());
    }
}
