package com.example.assignment3.mySql.patients.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class PatientDto {
    private Long id;
    private String name;
    private Long identityCardNo;
    private Long personalNumCode;
    private LocalDate dateOfBirth;
    private String address;
}
