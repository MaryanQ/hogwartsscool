package edu.hogwarts.service;

import edu.hogwarts.Repository.StudentRepository;
import edu.hogwarts.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentDTO createStudent(StudentDTO studentDTO) {

        if (studentDTO.getFullName() != null && !studentDTO.getFullName().isEmpty()) {
            String[] names = studentDTO.getFullName().split("\\s+");
            if (names.length >= 1) {
                studentDTO.setFirstName(names[0]);
            }
            if (names.length >= 2) {
                studentDTO.setMiddleName(names[1]);
            }
            if (names.length >= 3) {
                studentDTO.setLastName(names[2]);
            }
        }

        // Perform any additional logic before saving the student
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
