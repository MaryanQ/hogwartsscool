package edu.hogwarts;


import edu.hogwarts.Repository.CourseRepository;
import edu.hogwarts.Repository.StudentRepository;
import edu.hogwarts.Repository.TeacherRepository;
import edu.hogwarts.Repository.HouseRepository;
import edu.hogwarts.dto.CourseDTO;
import edu.hogwarts.dto.HouseDTO;
import edu.hogwarts.dto.TeacherDTO;
import edu.hogwarts.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class StudentAdminApplications {

    public static void main(String[] args) {
        SpringApplication.run(StudentAdminApplications.class, args);
    }

    @Component
    public static class DataInitializer implements CommandLineRunner {

        private final TeacherRepository teacherRepository;
        private final CourseRepository courseRepository;
        private final HouseRepository houseRepository;
        private final StudentRepository studentRepository;

        @Autowired
        public DataInitializer(TeacherRepository teacherRepository,
                               CourseRepository courseRepository, HouseRepository houseRepository, StudentRepository studentRepository) {

            this.teacherRepository = teacherRepository;
            this.courseRepository = courseRepository;
            this.houseRepository = houseRepository;
            this.studentRepository = studentRepository;
        }

        @Override
        public void run(String... args) throws Exception {
            teachers();
            courses();
            houses();
            students();
        }

        private void teachers() {
            List<TeacherDTO> teachers = new ArrayList<>();
            teachers.add(new TeacherDTO("Minerva", "", "McGonagall", LocalDate.of(1935, 10, 4), "Gryffindor", true, LocalDate.now(), LocalDate.now()));
            teachers.add(new TeacherDTO("Severus", "", "Snape", LocalDate.of(1960, 1, 9), "Slytherin", false, LocalDate.now(), LocalDate.now()));
            teachers.add(new TeacherDTO("Filius", "", "Flitwick", LocalDate.of(1958, 10, 17), "Ravenclaw", false, LocalDate.now(), LocalDate.now()));
            teachers.add(new TeacherDTO("Pomona", "", "Sprout", LocalDate.of(1941, 5, 15), "Hufflepuff", false, LocalDate.now(), LocalDate.now()));
            teachers.add(new TeacherDTO("Remus", "", "Lupin", LocalDate.of(1960, 3, 10), "Gryffindor", false, LocalDate.now(), LocalDate.now()));
            teachers.add(new TeacherDTO("Alastor", "Mad-Eye", "Moody", LocalDate.of(1945, 3, 17), "N/A", false, LocalDate.now(), LocalDate.now()));

            teacherRepository.saveAll(teachers);
        }

        private void courses() {
            List<CourseDTO> courses = new ArrayList<>();
            courses.add(new CourseDTO("Potions", true));
            courses.add(new CourseDTO("Transfiguration", true));
            courses.add(new CourseDTO("Charms", true));

            courseRepository.saveAll(courses);
        }

        private void houses() {
            List<HouseDTO> houses = new ArrayList<>();
            houses.add(new HouseDTO("Gryffindor", "Godric Gryffindor", "Scarlet and gold"));
            houses.add(new HouseDTO("Hufflepuff", "Helga Hufflepuff", "Yellow and black"));
            houses.add(new HouseDTO("Ravenclaw", "Rowena Ravenclaw", "Blue and bronze"));
            houses.add(new HouseDTO("Slytherin", "Salazar Slytherin", "Emerald green and silver"));

            houseRepository.saveAll(houses);
        }



        private void students() {
            HouseDTO gryffindor = houseRepository.findByName("Gryffindor");
            HouseDTO hufflepuff = houseRepository.findByName("Hufflepuff");


            List<StudentDTO> students = new ArrayList<>();
            students.add(new StudentDTO("Harry", "James", "Potter", LocalDate.of(1980, 7, 31), gryffindor, "Harry James Potter"));
            students.add(new StudentDTO("Hermione", "Jean", "Granger", LocalDate.of(1979, 9, 19), gryffindor, "Hermione Jean Granger"));
            students.add(new StudentDTO("Ron", "Bilius", "Weasley", LocalDate.of(1980, 3, 1), gryffindor, "Ron Bilius Weasley"));


            studentRepository.saveAll(students);


        }}}



