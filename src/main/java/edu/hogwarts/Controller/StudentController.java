package edu.hogwarts.Controller;

import edu.hogwarts.Repository.HouseRepository;
import edu.hogwarts.dto.HouseDTO;
import edu.hogwarts.dto.StudentDTO;
import edu.hogwarts.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private HouseRepository houseRepository;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") int id) {
        Optional<StudentDTO> optionalStudentDTO = studentService.getStudentById(id);
        if (optionalStudentDTO.isPresent()) {
            StudentDTO studentDTO = optionalStudentDTO.get();
            return new ResponseEntity<>(studentDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Map<String, Object> studentData) {

        String fullName = (String) studentData.get("fullName");
        LocalDate dateOfBirth = LocalDate.parse((String) studentData.get("dateOfBirth"));
        boolean graduated = (boolean) studentData.get("graduated");
        int graduationYear = (int) studentData.get("graduationYear");
        String houseName = (String) studentData.get("house");


        HouseDTO house = houseRepository.findByName(houseName);


        StudentDTO studentDTO = new StudentDTO(fullName, dateOfBirth, house, graduated, graduationYear);


        StudentDTO createdStudent = studentService.createStudent(studentDTO);

        if (createdStudent != null) {
            return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create student", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") int id, @RequestBody StudentDTO studentDTO) {
        studentDTO.setId(id);
        StudentDTO updatedStudent = studentService.saveOrUpdateStudent(studentDTO);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") int id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

