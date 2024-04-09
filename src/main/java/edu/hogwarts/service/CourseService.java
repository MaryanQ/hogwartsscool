package edu.hogwarts.service;

import edu.hogwarts.Repository.CourseRepository;
import edu.hogwarts.dto.CourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;


    public Optional<CourseDTO> getCourseById(Long id) {
        return courseRepository.findById(id.intValue());
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll();
    }


    public Optional<CourseDTO> getCourseById(int id) {
        return getCourseById((long) id);
    }

    public CourseDTO saveOrUpdateCourse(CourseDTO courseDTO) {
        return courseRepository.save(courseDTO);
    }

    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }
}


