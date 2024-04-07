package edu.hogwarts.Repository;

import edu.hogwarts.dto.TeacherDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherDTO, Integer> {

}
