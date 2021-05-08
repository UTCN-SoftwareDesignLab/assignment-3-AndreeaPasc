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
    private Long identity_card_no;

    @Column
    private Long personal_num_code;

    @Column
    private LocalDate date_of_birth;

    @Column
    private String address;
}
