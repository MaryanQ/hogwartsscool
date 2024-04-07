package edu.hogwarts.Controller;

import edu.hogwarts.dto.HouseDTO;
import edu.hogwarts.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/houses")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @GetMapping
    public ResponseEntity<List<HouseDTO>> getAllHouses() {
        List<HouseDTO> houses = houseService.getAllHouses();
        return new ResponseEntity<>(houses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HouseDTO> getHouseById(@PathVariable("id") int id) {
        Optional<HouseDTO> houseDTO = houseService.getHouseById(id);
        return houseDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
