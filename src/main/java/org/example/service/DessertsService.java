package org.example.service;

import org.example.model.dto.CategoryDTO;
import org.example.model.dto.DessertDTO;
import org.example.model.entity.Dessert;
import org.example.repository.DessertsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DessertsService {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    DessertsRepository dessertsRepository;

    public List<DessertDTO> getDesserts(){
        List<Dessert> desserts = dessertsRepository.findAll();

        return desserts.stream()
                .map(category -> modelMapper.map(desserts, DessertDTO.class))
                .toList();
    }

}
