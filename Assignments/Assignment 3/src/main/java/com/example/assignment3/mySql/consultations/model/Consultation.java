package com.example.assignment3.mySql.consultations.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Table(schema = "consultations")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long patient_id;

    @Column
    private Long doctor_id;

    @ElementCollection
    private List<Integer> consultationHour;
}
