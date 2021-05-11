package com.example.assignment3.mySql.consultations.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ConsultationDto {

    private Long id;
    private String patientName;
    private String doctorName;
    private Integer startHour;
    private Integer endHour;

}
