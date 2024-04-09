package edu.hogwarts.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String fullName;

    @JsonIgnore
    private Integer schoolYear;

    private Boolean graduated;

    private Integer graduationYear;


    @ManyToOne
    @JoinColumn(name = "house_id")
    private HouseDTO house;

    @ManyToMany
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<CourseDTO> courses = new HashSet<>();


    public StudentDTO() {
    }

    public StudentDTO(String fullName, LocalDate dateOfBirth, HouseDTO house, Boolean graduated, Integer graduationYear) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.house = house;
        this.graduated = graduated;
        this.graduationYear = graduationYear;
        parseFullName(fullName);
    }

    public String getHouse() {
        return house != null ? house.getName() : null;
    }

    private void parseFullName(String fullName) {
        String[] parts = fullName.split("\\s+");
        if (parts.length >= 1) {
            this.firstName = parts[0];
        }
        if (parts.length >= 2) {
            this.lastName = parts[parts.length - 1];
            StringBuilder middleNameBuilder = new StringBuilder();
            for (int i = 1; i < parts.length - 1; i++) {
                if (i > 1) {
                    middleNameBuilder.append(" ");
                }
                middleNameBuilder.append(parts[i]);
            }
            this.middleName = middleNameBuilder.toString();
        }
    }
    }





