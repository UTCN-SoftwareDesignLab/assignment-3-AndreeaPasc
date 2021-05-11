package com.example.assignment3.patient;

import com.example.assignment3.BaseControllerTest;
import com.example.assignment3.TestCreationFactory;
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

import static com.example.assignment3.UrlMapping.ENTITY;
import static com.example.assignment3.UrlMapping.PATIENTS;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PatientControllerTest extends BaseControllerTest {

    @InjectMocks
    private PatientController controller;

    @Mock
    private PatientService patientService;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        controller = new PatientController(patientService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void allItems() throws Exception {
        List<PatientDto> patients = TestCreationFactory.listOf(Patient.class);
        when(patientService.findAll()).thenReturn(patients);

        ResultActions response = mockMvc.perform(get(PATIENTS));

        response.andExpect(status().isOk())
                .andExpect(jsonContentToBe(patients));

    }

    @Test
    void create() throws Exception {
        PatientDto reqPatient = PatientDto.builder()
                .id(5L)
                .name("Patient1")
                .address("Address1")
                .dateOfBirth(LocalDate.now())
                .identityCardNo(1234L)
                .personalNumCode(5678L)
                .build();

        when(patientService.create(reqPatient)).thenReturn(reqPatient);

        ResultActions result = performPostWithRequestBody(PATIENTS, reqPatient);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(reqPatient));
    }

    @Test
    void update() throws Exception {
        PatientDto reqPatient = PatientDto.builder()
                .id(5L)
                .name("Patient1")
                .address("Address1")
                .dateOfBirth(LocalDate.now())
                .identityCardNo(1234L)
                .personalNumCode(5678L)
                .build();

        when(patientService.update(reqPatient.getId(), reqPatient)).thenReturn(reqPatient);

        ResultActions result = performPatchWithRequestBodyPathVariable(PATIENTS + ENTITY, reqPatient.getId().toString(), reqPatient );
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(reqPatient));
    }

    @Test
    void delete() throws Exception {
        PatientDto reqPatient = PatientDto.builder()
                .id(5L)
                .name("Patient1")
                .address("Address1")
                .dateOfBirth(LocalDate.now())
                .identityCardNo(1234L)
                .personalNumCode(5678L)
                .build();

        ResultActions result = performDeleteWithPathVariable(PATIENTS + ENTITY, reqPatient.getId().toString());
        result.andExpect(status().isOk());
    }
}
