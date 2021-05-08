package com.example.assignment3.mySql.patients.repository;

import com.example.assignment3.mySql.patients.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
