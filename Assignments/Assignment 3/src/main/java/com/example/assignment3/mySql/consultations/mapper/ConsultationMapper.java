package com.example.assignment3.mySql.consultations.mapper;

import com.example.assignment3.mySql.consultations.model.Consultation;
import com.example.assignment3.mySql.consultations.model.dto.ConsultationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface ConsultationMapper {

    ConsultationDto toDto(Consultation consultation);
    Consultation fromDto(ConsultationDto consultation);

}
