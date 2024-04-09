package edu.hogwarts.service;

import edu.hogwarts.Repository.StudentRepository;
import edu.hogwarts.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDTO> findStudentsByName(String name) {

        return studentRepository.findByName(name);
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        if (studentDTO.getFullName() == null || studentDTO.getFullName().trim().isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be empty");
        }


        String[] parts = studentDTO.getFullName().split("\\s+");
        String firstName = parts[0];
        String lastName = parts[parts.length - 1];
        String middleName = (parts.length > 2) ? String.join(" ", Arrays.copyOfRange(parts, 1, parts.length - 1)) : "";


        studentDTO.setFirstName(firstName);
        studentDTO.setMiddleName(middleName);
        studentDTO.setLastName(lastName);

        return studentRepository.save(studentDTO);
    }


    public List<StudentDTO> getAllStudents () {
            return studentRepository.findAll();
        }


        public Optional<StudentDTO> getStudentById ( int id) {
            return studentRepository.findById(id);
        }

            public StudentDTO saveOrUpdateStudent (StudentDTO studentDTO){
                return studentRepository.save(studentDTO);
            }

            public void deleteStudent ( int id){
                studentRepository.deleteById(id);
            }
        }
