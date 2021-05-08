package com.example.assignment3.mySql.patients.mapper;

import com.example.assignment3.mySql.patients.model.Patient;
import com.example.assignment3.mySql.patients.model.dto.PatientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDto toDto(Patient patient);
    Patient fromDto(PatientDto patient);

}
