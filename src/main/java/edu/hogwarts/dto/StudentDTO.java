package edu.hogwarts.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class StudentDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;

    @JsonIgnore
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private HouseDTO house;

    // Constructor without house parameter
    public StudentDTO() {
    }

    // Constructor with house parameter replaced by HouseDTO object
    public StudentDTO(String firstName, String middleName, String lastName, LocalDate dateOfBirth, HouseDTO house, String fullName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.house = house;
        this.fullName = generateFullName(firstName, middleName, lastName);
    }

    private String generateFullName(String firstName, String middleName, String lastName) {
        StringBuilder fullNameBuilder = new StringBuilder();
        if (firstName != null && !firstName.isEmpty()) {
            fullNameBuilder.append(firstName);
        }
        if (middleName != null && !middleName.isEmpty()) {
            if (!fullNameBuilder.isEmpty()) {
                fullNameBuilder.append(" ");
            }
            fullNameBuilder.append(middleName);
        }
        if (lastName != null && !lastName.isEmpty()) {
            if (!fullNameBuilder.isEmpty()) {
                fullNameBuilder.append(" ");
            }
            fullNameBuilder.append(lastName);
        }
        return fullNameBuilder.toString();
    }
}
