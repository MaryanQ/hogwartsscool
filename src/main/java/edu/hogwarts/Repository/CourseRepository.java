package edu.hogwarts.Repository;

import edu.hogwarts.dto.CourseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseDTO, Integer> {

    List<CourseDTO> findByName(String name);

}
