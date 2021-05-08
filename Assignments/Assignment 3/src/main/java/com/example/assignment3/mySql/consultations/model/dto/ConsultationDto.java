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
    private Long patientId;
    private Long doctorId;
    private List<Integer> consultationHour;

}
