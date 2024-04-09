package edu.hogwarts.service;

import edu.hogwarts.Repository.HouseRepository;
import edu.hogwarts.dto.HouseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HouseService {

    @Autowired
    private HouseRepository houseRepository;

    public List<HouseDTO> getAllHouses() {
        return houseRepository.findAll();
    }

    public HouseDTO getHouseByName(String name) {
        return houseRepository.findByName(name);
    }


    public Optional<HouseDTO> getHouseById(int id) {
        return houseRepository.findById(Long.valueOf(id));
    }
}