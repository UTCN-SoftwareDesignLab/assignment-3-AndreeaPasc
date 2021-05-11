package com.example.assignment3.mySql.consultations.service;

import com.example.assignment3.mySql.consultations.mapper.ConsultationMapper;
import com.example.assignment3.mySql.consultations.model.Consultation;
import com.example.assignment3.mySql.consultations.model.dto.ConsultationDto;
import com.example.assignment3.mySql.consultations.repository.ConsultationRepository;
import com.example.assignment3.mySql.patients.model.dto.PatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultationService {


    private final ConsultationRepository consultationRepository;
    private final ConsultationMapper consultationMapper;

    private Consultation findById(Long id) {
        return consultationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consultation not found: " + id));
    }

    public ConsultationDto create(ConsultationDto consultation){
        return consultationMapper.toDto(consultationRepository.save(consultationMapper.fromDto(consultation)));
    }

    public ConsultationDto update(Long id, ConsultationDto consultation){
        Consultation actConsultation = findById(id);
        actConsultation.setPatientName(consultation.getPatientName());
        actConsultation.setDoctorName(consultation.getDoctorName());
        actConsultation.setStartHour(consultation.getStartHour());
        actConsultation.setEndHour(consultation.getEndHour());

        return consultationMapper.toDto(consultationRepository.save(actConsultation));
    }

    public void delete(Long id){
        consultationRepository.deleteById(id);
    }

    public List<ConsultationDto> findAll(){
        return consultationRepository.findAll().stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());
    }
}
