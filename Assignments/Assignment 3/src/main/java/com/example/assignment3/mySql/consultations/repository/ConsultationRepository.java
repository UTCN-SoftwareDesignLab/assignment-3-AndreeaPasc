package com.example.assignment3.mySql.consultations.repository;

import com.example.assignment3.mySql.consultations.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
