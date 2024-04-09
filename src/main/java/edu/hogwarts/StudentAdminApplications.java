package edu.hogwarts;


import edu.hogwarts.Repository.CourseRepository;
import edu.hogwarts.Repository.HouseRepository;
import edu.hogwarts.Repository.StudentRepository;
import edu.hogwarts.Repository.TeacherRepository;
import edu.hogwarts.dto.CourseDTO;
import edu.hogwarts.dto.HouseDTO;
import edu.hogwarts.dto.StudentDTO;
import edu.hogwarts.dto.TeacherDTO;
import edu.hogwarts.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        private final HouseService houseService;
        private Collectors Collectors;

        @Autowired
        public DataInitializer(TeacherRepository teacherRepository,
                               CourseRepository courseRepository, HouseRepository houseRepository,
                               StudentRepository studentRepository, HouseService houseService) {

            this.teacherRepository = teacherRepository;
            this.courseRepository = courseRepository;
            this.houseRepository = houseRepository;
            this.studentRepository = studentRepository;
            this.houseService = houseService;
        }


        @Override
        public void run(String... args) throws Exception {
            houses();
            students();
            teachersAndCourses();
        }

        private void teachersAndCourses() {

            HouseDTO gryffindor = houseRepository.findByName("Gryffindor");
            HouseDTO slytherin = houseRepository.findByName("Slytherin");
            HouseDTO ravenclaw = houseRepository.findByName("Ravenclaw");
            HouseDTO hufflepuff = houseRepository.findByName("Hufflepuff");


            TeacherDTO minerva = new TeacherDTO("Minerva", "", "McGonagall", LocalDate.of(1935, 10, 4), gryffindor, true, LocalDate.of(1956, 9, 1), LocalDate.of(1998, 6, 30));
            TeacherDTO snape = new TeacherDTO("Severus", "", "Snape", LocalDate.of(1960, 1, 9), slytherin, false, LocalDate.of(1981, 9, 1), LocalDate.of(1998, 6, 30));
            TeacherDTO flitwick = new TeacherDTO("Filius", "", "Flitwick", LocalDate.of(1958, 10, 17), ravenclaw, false, LocalDate.of(1978, 9, 1), LocalDate.of(1998, 6, 30));
            TeacherDTO sprout = new TeacherDTO("Pomona", "", "Sprout", LocalDate.of(1941, 5, 15), hufflepuff, false, LocalDate.of(1961, 9, 1), LocalDate.of(1998, 6, 30));
            TeacherDTO lupin = new TeacherDTO("Remus", "", "Lupin", LocalDate.of(1960, 3, 10), gryffindor, false, LocalDate.of(1979, 9, 1), LocalDate.of(1998, 6, 30));
            TeacherDTO moody = new TeacherDTO("Alastor", "Mad-Eye", "Moody", LocalDate.of(1945, 3, 17), gryffindor, false, LocalDate.of(1965, 9, 1), LocalDate.of(1997, 6, 30));

            teacherRepository.saveAll(Arrays.asList(minerva, snape, flitwick, sprout, lupin, moody));


            CourseDTO potions = new CourseDTO("Potions", true);
            potions.setTeacher(snape);
            CourseDTO transfiguration = new CourseDTO("Transfiguration", true);
            transfiguration.setTeacher(minerva);
            CourseDTO charms = new CourseDTO("Charms", true);
            charms.setTeacher(flitwick);

            courseRepository.saveAll(Arrays.asList(potions, transfiguration, charms));


            enrollStudentsToCourse(potions, Arrays.asList("Harry James Potter", "Draco Lucius Malfoy"));
            enrollStudentsToCourse(transfiguration, Arrays.asList("Harry James Potter", "Hermione Jean Granger"));
            enrollStudentsToCourse(charms, Arrays.asList("Hermione Jean Granger", "Luna Lovegood"));
        }

        private void enrollStudentsToCourse(CourseDTO course, List<String> studentNames) {
            List<StudentDTO> allStudents = studentRepository.findAll();
            List<StudentDTO> studentsToEnroll = allStudents.stream()
                    .filter(student -> studentNames.contains(student.getName()))
                    .toList();

            course.getStudents().addAll(studentsToEnroll);
            courseRepository.save(course);


            System.out.println("Enrolled students in course " + course.getName() + ":");
            studentsToEnroll.forEach(student -> System.out.println("- " + student.getName()));
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
            students.add(new StudentDTO("Harry James Potter", LocalDate.of(1980, 7, 31), gryffindor, true, 1998));
            students.add(new StudentDTO("Hermione Jean Granger", LocalDate.of(1979, 9, 19), gryffindor, true, 1998));
            students.add(new StudentDTO("Ron Bilius Weasley", LocalDate.of(1980, 3, 1), gryffindor, true, 1998));
            students.add(new StudentDTO("Draco Lucius Malfoy", LocalDate.of(1980, 6, 5), gryffindor, true, 1998));
            students.add(new StudentDTO("Luna Lovegood", LocalDate.of(1981, 2, 13), gryffindor, false, null));
            students.add(new StudentDTO("Neville Longbottom", LocalDate.of(1980, 7, 30), gryffindor, true, 1998));

            studentRepository.saveAll(students);
        }
    }}
