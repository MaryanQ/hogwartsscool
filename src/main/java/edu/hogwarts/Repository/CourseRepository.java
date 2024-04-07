package edu.hogwarts.Repository;

import edu.hogwarts.dto.CourseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseDTO, Integer> {

}
