package edu.hogwarts.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class HouseDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String founder;
    private String colors;


    public HouseDTO() {

    }


    public HouseDTO( String name, String founder, String colors) {

        this.name = name;
        this.founder = founder;
        this.colors = colors;
    }
}
