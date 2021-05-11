package com.example.assignment3;

import com.example.assignment3.mySql.consultations.model.Consultation;
import com.example.assignment3.mySql.consultations.model.Doctor;
import com.example.assignment3.mySql.consultations.model.dto.ConsultationDto;
import com.example.assignment3.mySql.patients.model.Patient;
import com.example.assignment3.mySql.patients.model.dto.PatientDto;
import com.example.assignment3.postgres.users.dto.UserListDTO;

import javax.print.Doc;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class TestCreationFactory {

    @SuppressWarnings("all")
    public static <T> List<T> listOf(Class cls) {
        return listOf(cls, (Object[]) null);
    }

    @SuppressWarnings("all")
    public static <T> List<T> listOf(Class cls, Object... parameters) {
        int nrElements = new Random().nextInt(10) + 5;
        Supplier<?> supplier;

        if (cls.equals(UserListDTO.class)) {
            supplier = TestCreationFactory::newUserListDTO;
        } else if (cls.equals(Patient.class)) {
            supplier = TestCreationFactory::newPatient;
        } else if (cls.equals(PatientDto.class)) {
            supplier = TestCreationFactory::newPatientDto;
        } else if (cls.equals(ConsultationDto.class)) {
            supplier = TestCreationFactory::newConsultationDto;
        } else if (cls.equals(Consultation.class)) {
            supplier = TestCreationFactory::newConsultation;
        } else if (cls.equals(Doctor.class)) {
            supplier = TestCreationFactory::newDoctor;
        } else {
            supplier = () -> new String("You failed.");
        }

        Supplier<?> finalSupplier = supplier;
        return IntStream.range(0, nrElements).mapToObj(i ->
                (T) finalSupplier.get()
        ).collect(Collectors.toSet()) // remove duplicates in case of Long for example
                .stream().collect(toList());
    }

    private static UserListDTO newUserListDTO() {
        return UserListDTO.builder()
                .id(randomLong())
                .username(randomString())
                .email(randomEmail())
                .build();
    }

    private static Patient newPatient() {
        return Patient.builder()
                .id(randomLong())
                .name(randomString())
                .address(randomString())
                .dateOfBirth(LocalDate.now())
                .identityCardNo(randomLong())
                .personalNumCode(randomLong())
                .build();
    }

    private static PatientDto newPatientDto() {
        return PatientDto.builder()
                .id(randomLong())
                .name(randomString())
                .address(randomString())
                .dateOfBirth(LocalDate.now())
                .identityCardNo(randomLong())
                .personalNumCode(randomLong())
                .build();
    }


    private static Consultation newConsultation() {
        return Consultation.builder()
                .id(randomLong())
                .doctorName(randomString())
                .patientName(randomString())
                .startHour(randomBoundedInt(23))
                .endHour(randomBoundedInt(23))
                .build();
    }

    private static ConsultationDto newConsultationDto() {
        return ConsultationDto.builder()
                .id(randomLong())
                .doctorName(randomString())
                .patientName(randomString())
                .startHour(randomBoundedInt(23))
                .endHour(randomBoundedInt(23))
                .build();
    }

    private static Doctor newDoctor(){
        List<Integer> l = new ArrayList<>();
        l.add(randomBoundedInt(23));
        return Doctor.builder()
                .id(randomLong())
                .name(randomString())
                .hoursAvailable(l)
                .build();
    }

    public static String randomEmail() {
        return randomString() + "@" + randomString() + ".com";
    }

    public static byte[] randomBytes() {
        byte[] bytes = new byte[Math.toIntExact(randomLong())];
        new Random().nextBytes(bytes);
        return bytes;
    }

    public static long randomLong() {
        return new Random().nextInt(1999);
    }

    public static Boolean randomBoolean() {
        return new Random().nextBoolean();
    }

    public static int randomBoundedInt(int upperBound) {
        return new Random().nextInt(upperBound);
    }

    public static String randomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
