package edu.hogwarts.Repository;

import edu.hogwarts.dto.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentDTO, Integer> {

}
