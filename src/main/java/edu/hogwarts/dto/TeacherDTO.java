package edu.hogwarts.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @JsonIgnore
    private String name;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private HouseDTO house;

    @OneToMany(mappedBy = "teacher")
    private Set<CourseDTO> courses = new HashSet<>();

    private boolean headOfHouse;
    private LocalDate employmentStart;
    private LocalDate employmentEnd;

    public TeacherDTO() {
    }


    public void setHouseName(String houseName) {
        if (this.house == null) {
            this.house = new HouseDTO();
        }
        this.house.setName(houseName);
    }

    public String getHouseName() {
        return house != null ? house.getName() : null;
    }



    public TeacherDTO(String firstName, String middleName, String lastName,
                      LocalDate dateOfBirth, HouseDTO house, boolean headOfHouse,
                      LocalDate employmentStart, LocalDate employmentEnd) {
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
