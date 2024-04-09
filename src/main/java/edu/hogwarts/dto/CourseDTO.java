package edu.hogwarts.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class CourseDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private boolean active;

    @ManyToMany(mappedBy = "courses")
    private Set<StudentDTO> students = new HashSet<>();

    @Setter
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonIgnore
    private TeacherDTO teacher;

    public CourseDTO() {

    }

    public CourseDTO(String name, boolean active) {
        this.name = name;
        this.active = active;
    }
}
