package edu.hogwarts.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.hogwarts.dto.HouseDTO;

@Repository
public interface HouseRepository extends JpaRepository<HouseDTO, Long> {
    HouseDTO findByName(String name);
}
