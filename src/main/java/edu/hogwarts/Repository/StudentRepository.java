package edu.hogwarts.Repository;

import edu.hogwarts.dto.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentDTO, Integer> {
    List<StudentDTO> findByName(String name);
}
