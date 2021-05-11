package com.example.assignment3.consultation;

import com.example.assignment3.BaseControllerTest;
import com.example.assignment3.TestCreationFactory;
import com.example.assignment3.mySql.consultations.controller.ConsultationController;
import com.example.assignment3.mySql.consultations.model.dto.ConsultationDto;
import com.example.assignment3.mySql.consultations.service.ConsultationService;
import com.example.assignment3.mySql.patients.controller.PatientController;
import com.example.assignment3.mySql.patients.model.Patient;
import com.example.assignment3.mySql.patients.model.dto.PatientDto;
import com.example.assignment3.mySql.patients.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static com.example.assignment3.UrlMapping.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ConsultationControllerTest extends BaseControllerTest {

    @InjectMocks
    private ConsultationController controller;

    @Mock
    private ConsultationService consultationService;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        controller = new ConsultationController(consultationService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void allItems() throws Exception {
        List<ConsultationDto> consultations = TestCreationFactory.listOf(Patient.class);
        when(consultationService.findAll()).thenReturn(consultations);

        ResultActions response = mockMvc.perform(get(PATIENTS));

        response.andExpect(status().isOk())
                .andExpect(jsonContentToBe(consultations));

    }

    @Test
    void create() throws Exception {
        ConsultationDto consultationDto = ConsultationDto.builder()
                .id(5L)
                .doctorName("Doctor1")
                .startHour(1)
                .endHour(2)
                .build();

        when(consultationService.create(consultationDto)).thenReturn(consultationDto);

        ResultActions result = performPostWithRequestBody(PATIENTS, consultationDto);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(consultationDto));
    }

    @Test
    void update() throws Exception {
        ConsultationDto consultationDto = ConsultationDto.builder()
                .id(5L)
                .doctorName("Doctor1")
                .startHour(1)
                .endHour(2)
                .build();

        when(consultationService.update(consultationDto.getId(), consultationDto)).thenReturn(consultationDto);

        ResultActions result = performPatchWithRequestBodyPathVariable(CONSULTATIONS + ENTITY, consultationDto.getId().toString(), consultationDto );
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(consultationDto));
    }

    @Test
    void delete() throws Exception {
        ConsultationDto consultationDto = ConsultationDto.builder()
                .id(5L)
                .doctorName("Doctor1")
                .startHour(1)
                .endHour(2)
                .build();

        ResultActions result = performDeleteWithPathVariable(CONSULTATIONS + ENTITY, consultationDto.getId().toString());
        result.andExpect(status().isOk());
    }
}
