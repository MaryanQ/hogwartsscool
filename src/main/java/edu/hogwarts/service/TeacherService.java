package edu.hogwarts.service;

import edu.hogwarts.Repository.TeacherRepository;
import edu.hogwarts.dto.TeacherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<TeacherDTO> getTeacherById(int id) {
        return teacherRepository.findById(id);
    }

    public TeacherDTO saveOrUpdateTeacher(TeacherDTO teacherDTO) {
        return teacherRepository.save(teacherDTO);
    }

    public void deleteTeacher(int id) {
        teacherRepository.deleteById(id);
    }
}
