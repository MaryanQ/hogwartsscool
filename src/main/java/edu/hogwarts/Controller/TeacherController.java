package edu.hogwarts.Controller;

import edu.hogwarts.Repository.HouseRepository;
import edu.hogwarts.dto.TeacherDTO;
import edu.hogwarts.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private HouseRepository houseRepository;


    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        List<TeacherDTO> teachers = teacherService.getAllTeachers();
        for (TeacherDTO teacher : teachers) {
            if (teacher.getHouse() != null) {
                teacher.setHouseName(teacher.getHouseName());
            }
        }
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable("id") int id) {
        Optional<TeacherDTO> teacherDTO = teacherService.getTeacherById(id);
        return teacherDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody TeacherDTO teacherDTO) {
        TeacherDTO createdTeacher = teacherService.saveOrUpdateTeacher(teacherDTO);
        return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable("id") int id, @RequestBody TeacherDTO teacherDTO) {
        teacherDTO.setId(id);
        TeacherDTO updatedTeacher = teacherService.saveOrUpdateTeacher(teacherDTO);
        return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TeacherDTO> partialUpdateTeacher(@PathVariable("id") int id, @RequestBody TeacherDTO teacherDTO) {
        Optional<TeacherDTO> existingTeacherOptional = teacherService.getTeacherById(id);
        if (existingTeacherOptional.isPresent()) {
            TeacherDTO existingTeacher = existingTeacherOptional.get();

            // Update headOfHouse if provided
            if (teacherDTO.isHeadOfHouse() != existingTeacher.isHeadOfHouse()) {
                existingTeacher.setHeadOfHouse(teacherDTO.isHeadOfHouse());
            }

            // Update employmentEnd if provided
            if (teacherDTO.getEmploymentEnd() != null) {
                existingTeacher.setEmploymentEnd(teacherDTO.getEmploymentEnd());
            }

            // Save the updated teacher
            TeacherDTO updatedTeacher = teacherService.saveOrUpdateTeacher(existingTeacher);
            return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable("id") int id) {
        teacherService.deleteTeacher(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


