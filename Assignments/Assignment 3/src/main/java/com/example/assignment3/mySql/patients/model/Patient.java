package com.example.assignment3.mySql.patients.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Table(schema = "consultations")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Long identityCardNo;

    @Column
    private Long personalNumCode;

    @Column
    private LocalDate dateOfBirth;

    @Column
    private String address;
}
