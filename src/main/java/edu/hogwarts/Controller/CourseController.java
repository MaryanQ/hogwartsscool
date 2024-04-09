package edu.hogwarts.Controller;

import edu.hogwarts.dto.*;
import edu.hogwarts.service.CourseService;
import edu.hogwarts.service.StudentService;
import edu.hogwarts.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable("id") int id) {
        Optional<CourseDTO> courseDTO = courseService.getCourseById(id);
        return courseDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        CourseDTO createdCourse = courseService.saveOrUpdateCourse(courseDTO);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/students")
    public ResponseEntity<Void> addStudentsToCourse(@PathVariable("id") Long courseId, @RequestBody List<StudentRequest> studentRequests) {

        Optional<CourseDTO> courseOptional = courseService.getCourseById(courseId);
        if (courseOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CourseDTO course = courseOptional.get();
        List<StudentDTO> studentsToAdd = new ArrayList<>();


        for (StudentRequest studentRequest : studentRequests) {
            if (studentRequest.getId() != -1) {

                Optional<StudentDTO> studentOptional = studentService.getStudentById(studentRequest.getId());
                studentOptional.ifPresent(studentsToAdd::add);
            } else if (studentRequest.getName() != null) {

                List<StudentDTO> foundStudents = studentService.findStudentsByName(studentRequest.getName());
                if (!foundStudents.isEmpty()) {

                    studentsToAdd.add(foundStudents.get(0));
                }
            }
        }


        course.getStudents().addAll(studentsToAdd);
        courseService.saveOrUpdateCourse(course);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/teacher")
    public ResponseEntity<Void> updateTeacherForCourse(@PathVariable("id") int courseId, @RequestBody TeacherRequest teacherRequest) {
        if (teacherRequest == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<CourseDTO> courseOptional = courseService.getCourseById(courseId);
        if (courseOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CourseDTO course = courseOptional.get();
        if (teacherRequest.getName() != null) {

            List<TeacherDTO> foundTeachers = teacherService.findTeachersByName(teacherRequest.getName());
            if (!foundTeachers.isEmpty()) {
                course.setTeacher(foundTeachers.get(0));
                courseService.saveOrUpdateCourse(course);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
        public ResponseEntity<CourseDTO> updateCourse ( @PathVariable("id") int id, @RequestBody CourseDTO courseDTO){
            courseDTO.setId(id);
            CourseDTO updatedCourse = courseService.saveOrUpdateCourse(courseDTO);
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCourse ( @PathVariable("id") int id){
            courseService.deleteCourse(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
