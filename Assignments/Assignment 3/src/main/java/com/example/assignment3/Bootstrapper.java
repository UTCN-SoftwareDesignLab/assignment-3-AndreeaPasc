package com.example.assignment3;

import com.example.assignment3.mySql.consultations.model.dto.ConsultationDto;
import com.example.assignment3.mySql.consultations.repository.ConsultationRepository;
import com.example.assignment3.mySql.consultations.service.ConsultationService;
import com.example.assignment3.mySql.patients.model.dto.PatientDto;
import com.example.assignment3.mySql.patients.repository.PatientRepository;
import com.example.assignment3.mySql.patients.service.PatientService;
import com.example.assignment3.postgres.users.model.ERole;
import com.example.assignment3.postgres.users.model.Role;
import com.example.assignment3.postgres.users.repository.RoleRepository;
import com.example.assignment3.postgres.users.repository.UserRepository;
import com.example.assignment3.security.AuthService;
import com.example.assignment3.security.dto.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {
    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    private final ConsultationRepository consultationRepository;

    private final PatientService patientService;

    private final ConsultationService consultationService;

    private final PatientRepository patientRepository;

    @Value("true")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {
            consultationRepository.deleteAll();
            patientRepository.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            authService.register(SignupRequest.builder()
                    .email("alex@email.com")
                    .username("alex")
                    .password("WooHoo1!")
                    .roles(Set.of("ADMIN"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("alex1@email.com")
                    .username("alex1")
                    .password("WooHoo1!")
                    .roles(Set.of("SECRETARY"))
                    .build());

            patientService.create(PatientDto.builder()
                    .name("Patient1")
                    .identityCardNo(123L)
                    .personalNumCode(548L)
                    .address("Address1")
                    .dateOfBirth(LocalDate.now())
                    .build());

            consultationService.create(ConsultationDto.builder().build());
        }
    }
}

