package edu.hogwarts.Repository;

import edu.hogwarts.dto.TeacherDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherDTO, Integer> {
    List<TeacherDTO> findTeachersByName(String name);
}

