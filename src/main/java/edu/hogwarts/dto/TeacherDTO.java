package edu.hogwarts.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class TeacherDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String house;
    private boolean headOfHouse;
    private LocalDate employmentStart;
    private LocalDate employmentEnd;


    public TeacherDTO() {

    }


    public TeacherDTO(String firstName, String middleName, String lastName,
                      LocalDate dateOfBirth, String house, boolean headOfHouse,
                      LocalDate employmentStart, LocalDate employmentEnd) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.house = house;
        this.headOfHouse = headOfHouse;
        this.employmentStart = employmentStart;
        this.employmentEnd = employmentEnd;
    }
}
