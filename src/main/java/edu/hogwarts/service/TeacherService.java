package edu.hogwarts.service;

import edu.hogwarts.Repository.TeacherRepository;
import edu.hogwarts.dto.TeacherDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public List<TeacherDTO> findTeachersByName(String name) {
        return teacherRepository.findTeachersByName(name);
    }
    public TeacherDTO saveOrUpdateTeacher(TeacherDTO teacherDTO) {
        if (teacherDTO.getId() == 0) {
            return teacherRepository.save(teacherDTO);
        } else {
            Optional<TeacherDTO> existingTeacherOptional = teacherRepository.findById(teacherDTO.getId());
            if (existingTeacherOptional.isPresent()) {
                TeacherDTO existingTeacher = existingTeacherOptional.get();

                BeanUtils.copyProperties(teacherDTO, existingTeacher, getNullPropertyNames(teacherDTO));
                return teacherRepository.save(existingTeacher);
            } else {

                return null;
            }
        }
    }

    public void deleteTeacher(int id) {
        teacherRepository.deleteById(id);
    }

    //
    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }


}